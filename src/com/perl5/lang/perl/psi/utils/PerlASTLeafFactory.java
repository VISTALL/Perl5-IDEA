package com.perl5.lang.perl.psi.utils;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.intellij.lang.ASTLeafFactory;
import com.intellij.lang.LanguageVersion;
import com.intellij.psi.impl.source.tree.LeafElement;
import com.intellij.psi.tree.IElementType;
import com.perl5.lang.perl.lexer.PerlElementTypes;
import com.perl5.lang.perl.psi.impl.PerlHeredocTerminatorElementImpl;
import com.perl5.lang.perl.psi.impl.PerlNamespaceElementImpl;
import com.perl5.lang.perl.psi.impl.PerlStringContentElementImpl;
import com.perl5.lang.perl.psi.impl.PerlSubNameElementImpl;
import com.perl5.lang.perl.psi.impl.PerlVariableNameElementImpl;
import com.perl5.lang.perl.psi.impl.PerlVersionElementImpl;

/**
 * @author VISTALL
 * @since 25-Sep-15
 */
public class PerlASTLeafFactory implements ASTLeafFactory, PerlElementTypes
{
	@NotNull
	@Override
	public LeafElement createLeaf(@NotNull IElementType type, @NotNull LanguageVersion<?> languageVersion, @NotNull CharSequence text)
	{
		if(type == STRING_CONTENT)
		{
			return new PerlStringContentElementImpl(type, text);
		}
		else if(type == VARIABLE_NAME)
		{
			return new PerlVariableNameElementImpl(type, text);
		}
		else if(type == SUB)
		{
			return new PerlSubNameElementImpl(type, text);
		}
		else if(type == PACKAGE)
		{
			return new PerlNamespaceElementImpl(type, text);
		}
		else if(type == VERSION_ELEMENT)
		{
			return new PerlVersionElementImpl(type, text);
		}
		else if(type == HEREDOC_END)
		{
			return new PerlHeredocTerminatorElementImpl(type, text);
		}
		throw new IllegalArgumentException(type.toString());
	}

	@Override
	public boolean apply(@Nullable IElementType iElementType)
	{
		return iElementType == STRING_CONTENT || iElementType == VARIABLE_NAME || iElementType == SUB || iElementType == PACKAGE || iElementType == VERSION_ELEMENT || iElementType == HEREDOC_END;
	}
}
