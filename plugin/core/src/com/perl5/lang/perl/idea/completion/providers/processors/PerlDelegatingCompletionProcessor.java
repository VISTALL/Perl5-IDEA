/*
 * Copyright 2015-2020 Alexandr Evstigneev
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

package com.perl5.lang.perl.idea.completion.providers.processors;

import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class PerlDelegatingCompletionProcessor<Delegate extends PerlCompletionProcessor>
  extends AbstractPerlCompletionProcessor {
  @NotNull
  private Delegate myDelegate;

  public PerlDelegatingCompletionProcessor(@NotNull Delegate delegate) {
    myDelegate = delegate;
  }

  @Override
  public @NotNull CompletionResultSet getResultSet() {
    return getDelegate().getResultSet();
  }

  @NotNull
  protected final Delegate getDelegate() {
    return myDelegate;
  }

  protected void setDelegate(@NotNull Delegate delegate) {
    myDelegate = delegate;
  }

  @Override
  public @NotNull PsiElement getLeafElement() {
    return getDelegate().getLeafElement();
  }

  @Override
  public boolean matches(@Nullable String suggestedName) {
    return getDelegate().matches(suggestedName);
  }

  @Override
  public void addElement(@NotNull LookupElementBuilder lookupElement) {
    getDelegate().addElement(lookupElement);
  }

  @Override
  public boolean result() {
    return getDelegate().result();
  }

  @Override
  public void logStatus(@NotNull Class<?> clazz) {
    getDelegate().logStatus(clazz);
  }

  @Override
  public abstract @NotNull Delegate withPrefixMatcher(@NotNull String prefix);
}