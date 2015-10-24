/*
 * Copyright 2015 Alexandr Evstigneev
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

package com.perl5.lang.perl.parser;

import com.intellij.lang.ASTNode;
import com.intellij.lang.Language;
import com.intellij.lang.LanguageVersion;
import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilderFactory;
import com.intellij.lang.PsiParser;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.ILazyParseableElementType;
import com.intellij.util.LanguageVersionUtil;
import com.perl5.lang.perl.PerlLanguage;
import com.perl5.lang.perl.lexer.PerlHeredocLexerAdapter;

/**
 * Created by hurricup on 13.08.2015.
 */
public class PerlHeredocElementType extends ILazyParseableElementType
{
	public PerlHeredocElementType(String name)
	{
		super(name, PerlLanguage.INSTANCE);
	}

	@Override
	public ASTNode parseContents(ASTNode chameleon)
	{
		PsiElement parentElement = chameleon.getTreeParent().getPsi();
		Project project = parentElement.getProject();
		LanguageVersion<Language> defaultVersion = LanguageVersionUtil.findDefaultVersion(getLanguage());
		PsiBuilder builder = PsiBuilderFactory.getInstance().createBuilder(
				project,
				chameleon,
				new PerlHeredocLexerAdapter(toString()),
				getLanguage(),
				defaultVersion,
				chameleon.getText());
		PsiParser parser = new PerlParser();

		return parser.parse(this, builder, defaultVersion).getFirstChildNode();
	}
}
