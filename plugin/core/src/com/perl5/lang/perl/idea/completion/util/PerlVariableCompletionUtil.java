/*
 * Copyright 2015-2019 Alexandr Evstigneev
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.perl5.lang.perl.idea.completion.util;

import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiElement;
import com.intellij.psi.scope.PsiScopeProcessor;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.Processor;
import com.perl5.lang.perl.idea.PerlCompletionWeighter;
import com.perl5.lang.perl.idea.completion.PerlInsertHandlers;
import com.perl5.lang.perl.idea.completion.providers.PerlDelegatingVariableCompletionProcessor;
import com.perl5.lang.perl.idea.completion.providers.PerlVariableCompletionProcessor;
import com.perl5.lang.perl.idea.completion.providers.PerlVariableCompletionProcessorImpl;
import com.perl5.lang.perl.idea.configuration.settings.PerlSharedSettings;
import com.perl5.lang.perl.idea.ui.PerlIconProvider;
import com.perl5.lang.perl.internals.PerlVersion;
import com.perl5.lang.perl.psi.*;
import com.perl5.lang.perl.psi.impl.PerlBuiltInVariable;
import com.perl5.lang.perl.psi.properties.PerlLexicalScope;
import com.perl5.lang.perl.psi.references.PerlBuiltInVariablesService;
import com.perl5.lang.perl.psi.utils.PerlResolveUtil;
import com.perl5.lang.perl.psi.utils.PerlVariableType;
import com.perl5.lang.perl.util.*;
import gnu.trove.THashSet;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Set;


public class PerlVariableCompletionUtil {

  @NotNull
  public static LookupElementBuilder createVariableLookupElement(@NotNull String name, @NotNull PerlVariableType variableType) {
    return LookupElementBuilder.create(PerlVariable.braceName(name)).withIcon(PerlIconProvider.getIcon(variableType));
  }

  @NotNull
  public static LookupElementBuilder createArrayElementLookupElement(@NotNull String name, @NotNull PerlVariableType variableType) {
    return createVariableLookupElement(name, variableType)
      .withInsertHandler(PerlInsertHandlers.ARRAY_ELEMENT_INSERT_HANDLER).withTailText("[]");
  }

  @NotNull
  public static LookupElementBuilder createHashElementLookupElement(@NotNull String name, @NotNull PerlVariableType variableType) {
    return createVariableLookupElement(name, variableType)
      .withInsertHandler(PerlInsertHandlers.HASH_ELEMENT_INSERT_HANDLER).withTailText("{}");
  }

  @NotNull
  private static String computeVariableName(@NotNull PerlVariableDeclarationElement element, boolean forceShortMain) {
    if (element.isGlobalDeclaration() && !(element instanceof PerlBuiltInVariable)) {
      return StringUtil.notNullize(PerlVariable.adjustName(element.getCanonicalName(), forceShortMain));
    }
    return PerlVariable.braceName(element.getVariableName());
  }

  /**
   * @param sigilToPrepend '_' means we don't need to prepend
   */
  @Nullable
  public static LookupElementBuilder createVariableLookupElement(@NotNull PerlVariableDeclarationElement element,
                                                                 char sigilToPrepend,
                                                                 @NotNull PerlVariableCompletionProcessor variableCompletionProcessor) {
    String variableName = computeVariableName(element, variableCompletionProcessor.isForceShortMain());
    if (!variableCompletionProcessor.matches(variableName)) {
      return null;
    }
    String lookupString = sigilToPrepend == '_' ? variableName :
                          sigilToPrepend + variableName;
    LookupElementBuilder elementBuilder = LookupElementBuilder.create(element, lookupString)
      .withIcon(PerlIconProvider.getIcon(element));
    return sigilToPrepend == '_' ? elementBuilder : elementBuilder.withLookupString(variableName);
  }

  @Nullable
  public static LookupElementBuilder createVariableLookupElement(@NotNull PerlGlobVariable typeGlob,
                                                                 boolean withSigil,
                                                                 @NotNull PerlVariableCompletionProcessor variableCompletionProcessor) {
    String variableName = StringUtil.notNullize(typeGlob.getCanonicalName());
    if (!variableCompletionProcessor.matches(variableName)) {
      return null;
    }
    String lookupString = withSigil ?
                          PerlVariableType.GLOB.getSigil() + variableName :
                          variableName;

    LookupElementBuilder elementBuilder = LookupElementBuilder.create(typeGlob, lookupString).withIcon(PerlIconProvider.getIcon(typeGlob));
    return withSigil ? elementBuilder.withLookupString(variableName) : elementBuilder;
  }


  @Nullable
  public static LookupElementBuilder createHashElementLookupElement(@NotNull PerlVariableDeclarationElement element,
                                                                    char sigilToPrepend,
                                                                    @NotNull PerlVariableCompletionProcessor variableCompletionProcessor) {
    LookupElementBuilder baseLookup = createVariableLookupElement(element, sigilToPrepend, variableCompletionProcessor);
    return baseLookup == null ? null : baseLookup
      .withInsertHandler(PerlInsertHandlers.HASH_ELEMENT_INSERT_HANDLER).withTailText("{}");
  }

  @Nullable
  public static LookupElementBuilder createArrayElementLookupElement(@NotNull PerlVariableDeclarationElement element,
                                                                     char sigilToPrepend,
                                                                     @NotNull PerlVariableCompletionProcessor variableCompletionProcessor) {
    LookupElementBuilder baseLookup = createVariableLookupElement(element, sigilToPrepend, variableCompletionProcessor);
    return baseLookup == null ? null : baseLookup
      .withInsertHandler(PerlInsertHandlers.ARRAY_ELEMENT_INSERT_HANDLER).withTailText("[]");
  }

  public static void fillWithUnresolvedVars(@NotNull PerlVariableCompletionProcessorImpl completionProcessor) {
    PsiElement variableNameElement = completionProcessor.getVariableNameElement();
    final PerlLexicalScope lexicalScope = PsiTreeUtil.getParentOfType(variableNameElement, PerlLexicalScope.class);
    PsiElement perlVariable = variableNameElement.getParent();
    final Set<String> collectedNames = new THashSet<>();

    if (lexicalScope != null && perlVariable instanceof PerlVariable) {
      final int minOffset = variableNameElement.getTextOffset();
      final PerlVariableType actualType = ((PerlVariable)perlVariable).getActualType();

      lexicalScope.accept(new PerlRecursiveVisitor() {
        @Override
        public void visitPerlVariable(@NotNull PerlVariable perlVariable) {
          if (perlVariable.isValid() &&
              !(perlVariable.getParent() instanceof PerlVariableDeclarationElement) &&
              perlVariable.getTextOffset() > minOffset &&
              actualType == perlVariable.getActualType()
            ) {
            String variableName = perlVariable.getName();
            if (completionProcessor.matches(variableName) &&
                collectedNames.add(variableName) &&
                perlVariable.getLexicalDeclaration() == null) {
              completionProcessor.process(LookupElementBuilder.create(variableName));
            }
          }
          super.visitPerlVariable(perlVariable);
        }
      });
    }
  }

  @NotNull
  public static <T extends LookupElement> T setLexical(@NotNull T element) {
    element.putUserData(PerlCompletionWeighter.WEIGHT, 1);
    return element;
  }

  public static void fillWithBuiltInVariables(@NotNull PerlVariableCompletionProcessor completionProcessor) {
    Processor<PerlVariableDeclarationElement> processor = createBuiltInVariablesLookupProcessor(completionProcessor);
    if (processor == null) {
      return;
    }

    PerlBuiltInVariablesService.getInstance(completionProcessor.getProject()).processVariables(processor);
  }

  public static void fillWithLexicalVariables(@NotNull PerlVariableCompletionProcessor variableCompletionProcessor) {
    PsiElement perlVariable = variableCompletionProcessor.getVariableElement();
    Processor<PerlVariableDeclarationElement> lookupProcessor = createLexicalLookupProcessor(
      variableCompletionProcessor);

    if (lookupProcessor == null) {
      return;
    }

    PsiScopeProcessor processor = (element, __) -> {
      if (!(element instanceof PerlVariableDeclarationElement)) {
        return true;
      }
      PerlVariableDeclarationElement variable = (PerlVariableDeclarationElement)element;
      PsiElement declarationStatement = PsiTreeUtil.getParentOfType(variable, PerlStatement.class);
      if (PsiTreeUtil.isAncestor(declarationStatement, perlVariable, false)) {
        return true;
      }

      if (StringUtil.isNotEmpty(variable.getName())) {
        return lookupProcessor.process(variable);
      }

      return true;
    };
    PerlResolveUtil.treeWalkUp(variableCompletionProcessor.getVariableNameElement(), processor);
  }


  /**
   * @return lookup generator for lexical variables
   * @see #createLookupGenerator(Processor, boolean, PerlVariableCompletionProcessor)
   */
  @Nullable
  private static Processor<PerlVariableDeclarationElement> createLexicalLookupProcessor(
    @NotNull PerlVariableCompletionProcessor variableCompletionProcessor) {
    return createVariableLookupProcessor(
      new PerlDelegatingVariableCompletionProcessor(variableCompletionProcessor) {
        @Override
        protected void addElement(@NotNull LookupElementBuilder lookupElement) {
          super.addElement(setLexical(lookupElement));
        }
      });
  }

  /**
   * @return lookup generator for built-in variables
   * @see #createLookupGenerator(Processor, boolean, PerlVariableCompletionProcessor)
   */
  @Nullable
  private static Processor<PerlVariableDeclarationElement> createBuiltInVariablesLookupProcessor(
    @NotNull PerlVariableCompletionProcessor perlVariableCompletionProcessor) {
    return createVariableLookupProcessor(
      new PerlDelegatingVariableCompletionProcessor(perlVariableCompletionProcessor) {
        @Override
        protected void addElement(@NotNull LookupElementBuilder lookupElement) {
          super.addElement(lookupElement.withBoldness(true));
        }
      });
  }

  /**
   * @return processor of variable declarations, generating lookup elements for them and feeding to the {@code lookupConsumer}
   */
  @Nullable
  private static Processor<PerlVariableDeclarationElement> createVariableLookupProcessor(
    @NotNull PerlVariableCompletionProcessor variableCompletionProcessor) {
    @Nullable PsiElement perlVariable = variableCompletionProcessor.getVariableElement();
    boolean addHashSlices = hasHashSlices(perlVariable);
    if (perlVariable instanceof PsiPerlMethod || perlVariable instanceof PsiPerlPerlHandleExpr) {
      return variable -> {
        PerlVariableType variableType = variable.getActualType();
        if (variableType == PerlVariableType.SCALAR) {
          return variableCompletionProcessor.process(
            createVariableLookupElement(variable, PerlVariableType.SCALAR.getSigil(), variableCompletionProcessor));
        }
        else if (variableType == PerlVariableType.ARRAY) {
          variableCompletionProcessor.process(
            createVariableLookupElement(variable, PerlVariableType.ARRAY.getSigil(), variableCompletionProcessor));
          variableCompletionProcessor.process(
            createArrayElementLookupElement(variable, PerlVariableType.SCALAR.getSigil(), variableCompletionProcessor));
          return variableCompletionProcessor.process(
            createArrayElementLookupElement(variable, PerlVariableType.ARRAY.getSigil(), variableCompletionProcessor));
        }
        else if (variable.getActualType() == PerlVariableType.HASH) {
          variableCompletionProcessor.process(
            createVariableLookupElement(variable, PerlVariableType.HASH.getSigil(), variableCompletionProcessor));
          variableCompletionProcessor.process(
            createHashElementLookupElement(variable, PerlVariableType.SCALAR.getSigil(), variableCompletionProcessor));
          return variableCompletionProcessor.process(
            createHashElementLookupElement(variable, PerlVariableType.ARRAY.getSigil(), variableCompletionProcessor));
        }
        return true;
      };
    }
    if (perlVariable instanceof PsiPerlScalarVariable) {
      return variable -> {
        if (variable.getActualType() == PerlVariableType.SCALAR) {
          return variableCompletionProcessor.process(createVariableLookupElement(variable, '_', variableCompletionProcessor));
        }
        else if (variable.getActualType() == PerlVariableType.ARRAY) {
          return variableCompletionProcessor.process(createArrayElementLookupElement(variable, '_', variableCompletionProcessor));
        }
        else if (variable.getActualType() == PerlVariableType.HASH) {
          return variableCompletionProcessor.process(createHashElementLookupElement(variable, '_', variableCompletionProcessor));
        }
        return true;
      };
    }
    else if (perlVariable instanceof PsiPerlArrayVariable) {
      return variable -> {
        if (variable.getActualType() == PerlVariableType.ARRAY) {
          variableCompletionProcessor.process(createVariableLookupElement(variable, '_', variableCompletionProcessor));
          return variableCompletionProcessor.process(createArrayElementLookupElement(variable, '_', variableCompletionProcessor));
        }
        else if (variable.getActualType() == PerlVariableType.HASH) {
          return variableCompletionProcessor.process(createHashElementLookupElement(variable, '_', variableCompletionProcessor));
        }
        return true;
      };
    }
    else if (perlVariable instanceof PsiPerlArrayIndexVariable) {
      return variable -> {
        if (variable.getActualType() == PerlVariableType.ARRAY) {
          return variableCompletionProcessor.process(createVariableLookupElement(variable, '_', variableCompletionProcessor));
        }
        return true;
      };
    }
    else if (perlVariable instanceof PsiPerlHashVariable) {
      return variable -> {
        PerlVariableType variableType = variable.getActualType();
        if (variableType == PerlVariableType.HASH) {
          return variableCompletionProcessor.process(createVariableLookupElement(variable, '_', variableCompletionProcessor)) &&
                 (!addHashSlices ||
                  variableCompletionProcessor.process(createHashElementLookupElement(variable, '_', variableCompletionProcessor)));
        }
        else if (addHashSlices && variableType == PerlVariableType.ARRAY) {
          return variableCompletionProcessor.process(createArrayElementLookupElement(variable, '_', variableCompletionProcessor));
        }
        return true;
      };
    }
    else if (perlVariable instanceof PerlGlobVariable) {
      return variable -> variableCompletionProcessor.process(createVariableLookupElement(variable, '_', variableCompletionProcessor));
    }
    return null;
  }

  /**
   * @return true iff 5.20 hash/array slices are enabled
   */
  private static boolean hasHashSlices(@NotNull PsiElement psiElement) {
    return !PerlSharedSettings.getInstance(psiElement).getTargetPerlVersion().lesserThan(PerlVersion.V5_20);
  }

  public static void fillWithFullQualifiedVariables(@NotNull PerlVariableCompletionProcessor variableCompletionProcessor) {
    PsiElement variableNameElement = variableCompletionProcessor.getVariableNameElement();
    PsiElement perlVariable = variableCompletionProcessor.getVariableElement();
    boolean forceShortMain = StringUtil.startsWith(variableNameElement.getNode().getChars(), PerlPackageUtil.NAMESPACE_SEPARATOR);
    Processor<PerlVariableDeclarationElement> lookupGenerator = createVariableLookupProcessor(
      new PerlDelegatingVariableCompletionProcessor(variableCompletionProcessor) {
        @Override
        public boolean isForceShortMain() {
          return forceShortMain;
        }
      });

    if (lookupGenerator == null) {
      return;
    }

    Project project = variableNameElement.getProject();
    GlobalSearchScope resolveScope = variableNameElement.getResolveScope();

    if (perlVariable instanceof PsiPerlScalarVariable) {
      PerlScalarUtil.processDefinedGlobalScalars(project, resolveScope, lookupGenerator);
      PerlArrayUtil.processDefinedGlobalArrays(project, resolveScope, lookupGenerator);
      PerlHashUtil.processDefinedGlobalHashes(project, resolveScope, lookupGenerator);
    }
    else if (perlVariable instanceof PerlGlobVariable || perlVariable instanceof PsiPerlMethod) {
      PerlScalarUtil.processDefinedGlobalScalars(project, resolveScope, lookupGenerator);
      PerlArrayUtil.processDefinedGlobalArrays(project, resolveScope, lookupGenerator);
      PerlHashUtil.processDefinedGlobalHashes(project, resolveScope, lookupGenerator);

      // globs
      PerlGlobUtil.processDefinedGlobsNames(project, resolveScope, typeglob ->
        variableCompletionProcessor.process(createVariableLookupElement(
          typeglob, perlVariable instanceof PsiPerlMethod, variableCompletionProcessor)));
    }
    else if (perlVariable instanceof PsiPerlArrayVariable) {
      PerlArrayUtil.processDefinedGlobalArrays(project, resolveScope, lookupGenerator);
      PerlHashUtil.processDefinedGlobalHashes(project, resolveScope, lookupGenerator);
    }
    else if (perlVariable instanceof PsiPerlArrayIndexVariable) {
      // global arrays
      PerlArrayUtil.processDefinedGlobalArrays(project, resolveScope, lookupGenerator);
    }
    else if (perlVariable instanceof PsiPerlHashVariable) {
      // global hashes
      PerlHashUtil.processDefinedGlobalHashes(project, resolveScope, lookupGenerator);
      if (hasHashSlices(perlVariable)) {
        PerlArrayUtil.processDefinedGlobalArrays(project, resolveScope, lookupGenerator);
      }
    }
  }
}
