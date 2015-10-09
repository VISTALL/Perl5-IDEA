/* The following code was generated by JFlex 1.4.3 on 09.10.15 12:35 */

/*
    Copyright 2015 Alexandr Evstigneev

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
*/
package com.perl5.lang.perl.lexer;

import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;



/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.4.3
 * on 09.10.15 12:35 from the specification file
 * <tt>D:/repository/Camelcade/src/com/perl5/lang/perl/lexer/Perl.flex</tt>
 */
public abstract class PerlLexerGenerated extends PerlBaseLexer
{
  /**
   * lexical states
   */
  public static final int LEX_MOJO_PERL_BLOCK = 28;
  public static final int LEX_QUOTE_LIKE_OPENER_Q = 12;
  public static final int LEX_CODE = 2;
  public static final int LEX_TRANS_OPENER = 20;
  public static final int LEX_HEREDOC_WAITING_QX = 8;
  public static final int LEX_MOJO_PERL_LINE_SEMI = 30;
  public static final int LEX_HEREDOC_WAITING_QQ = 6;
  public static final int LEX_FORMAT_WAITING = 10;
  public static final int LEX_REGEX_OPENER = 22;
  public static final int LEX_QUOTE_LIKE_OPENER_QX = 16;
  public static final int LEX_MOJO_PERL_LINE = 26;
  public static final int LEX_QUOTE_LIKE_OPENER_QW = 18;
  public static final int LEX_HEREDOC_WAITING = 4;
  public static final int LEX_QUOTE_LIKE_OPENER_QQ = 14;
  public static final int LEX_PREPARSED_ITEMS = 34;
  public static final int LEX_HTML_BLOCK = 24;
  public static final int LEX_MOJO_PERL_BLOCK_SEMI = 32;
  public static final int YYINITIAL = 0;
  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;
  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = {
          0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
          0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
          0,  0,  0, 0
  };

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED =
          "\11\0\1\3\1\2\1\0\1\3\1\1\22\0\1\3\1\37\1\27" +
                  "\1\51\1\50\1\46\1\40\1\10\1\56\1\57\1\45\1\16\1\35" +
                  "\1\5\1\14\1\44\1\17\1\23\10\11\1\7\1\32\1\31\1\33" +
                  "\1\34\1\42\1\47\3\26\1\21\1\15\1\21\6\4\1\24\1\4" +
                  "\1\24\2\4\3\24\2\4\2\24\2\4\1\54\1\43\1\55\1\6" +
                  "\1\12\1\30\1\21\1\22\2\26\1\25\1\26\1\24\3\4\2\24" +
                  "\2\4\2\24\1\4\4\24\1\13\1\24\1\20\1\4\1\24\1\52" +
                  "\1\41\1\53\1\36\uff81\0";

  /** 
   * Translates characters to character classes
   */
  private static final char[] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);
  private static final String ZZ_ACTION_PACKED_0 =
          "\1\0\2\1\1\2\1\3\1\4\1\5\1\6\1\7" +
                  "\1\10\1\11\1\4\1\12\1\13\1\11\1\4\1\14" +
                  "\1\15\1\16\1\17\1\20\1\21\1\22\1\23\1\24" +
                  "\1\25\1\26\1\27\1\30\1\31\1\32\1\33\1\34" +
                  "\1\35\1\36\1\37\1\40\1\41\1\42\1\43\2\0" +
                  "\1\4\1\44\1\4\1\45\1\46\1\47\2\50\1\4" +
                  "\1\51\1\50\1\52\1\0\1\53\3\4\1\54\1\0" +
                  "\1\55\1\56\1\57\1\60\1\61\1\62\1\63\1\64" +
                  "\1\65\1\0\1\66\2\0\1\47\1\50\1\0\1\50" +
                  "\1\0\1\67\3\50\5\70\1\0\1\71\3\0\1\72" +
                  "\1\51\1\71";
  /**
   * Translates DFA states to action switch labels.
   */
  private static final int[] ZZ_ACTION = zzUnpackAction();
  private static final String ZZ_ROWMAP_PACKED_0 =
          "\0\0\0\60\0\140\0\60\0\220\0\300\0\360\0\u0120" +
                  "\0\u0150\0\60\0\u0180\0\u01b0\0\u01e0\0\u0210\0\u0240\0\u0270" +
                  "\0\60\0\60\0\u02a0\0\60\0\u02d0\0\u0300\0\60\0\60" +
                  "\0\u0330\0\u0360\0\u0390\0\60\0\60\0\60\0\60\0\60" +
                  "\0\60\0\u03c0\0\60\0\60\0\60\0\60\0\60\0\60" +
                  "\0\u03f0\0\u0420\0\u0450\0\u0480\0\u04b0\0\60\0\u0120\0\u04e0" +
                  "\0\u0510\0\u0540\0\u0570\0\u05a0\0\u05d0\0\u0600\0\u0630\0\60" +
                  "\0\u0660\0\u0690\0\u06c0\0\u06f0\0\u0720\0\60\0\60\0\60" +
                  "\0\60\0\60\0\60\0\60\0\u0750\0\u0750\0\u0480\0\60" +
                  "\0\u0780\0\u0150\0\u07b0\0\u07e0\0\u0810\0\u0840\0\u0870\0\60" +
                  "\0\u0810\0\u0660\0\u0690\0\60\0\u03f0\0\u0420\0\u06c0\0\300" +
                  "\0\u08a0\0\u08d0\0\u0900\0\u0930\0\u0960\0\60\0\u0990\0\60";
  /**
   * Translates a state to a row index in the transition table
   */
  private static final int[] ZZ_ROWMAP = zzUnpackRowMap();
  private static final String ZZ_TRANS_PACKED_0 =
          "\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11" +
                  "\1\12\1\13\1\6\1\14\1\15\1\6\1\16\1\17" +
                  "\1\20\2\6\1\13\3\6\1\21\1\22\1\23\1\24" +
                  "\1\25\1\26\1\27\1\30\1\31\1\32\1\33\1\34" +
                  "\1\35\1\36\1\37\1\40\1\41\1\42\1\2\1\43" +
                  "\1\44\1\45\1\46\1\47\1\50\62\0\1\4\60\0" +
                  "\1\5\60\0\1\6\2\0\1\51\1\52\3\6\1\0" +
                  "\1\6\1\0\10\6\35\0\1\53\1\54\3\0\3\53" +
                  "\1\0\1\53\1\0\1\53\1\55\1\53\1\55\1\53" +
                  "\3\55\5\0\1\56\27\0\1\57\4\0\3\57\1\0" +
                  "\1\57\1\0\10\57\40\0\1\60\54\0\1\6\2\0" +
                  "\1\51\1\52\1\13\1\61\1\6\1\62\1\63\1\0" +
                  "\1\13\3\6\1\13\1\6\1\63\1\6\35\0\1\6" +
                  "\2\0\1\51\1\52\1\64\2\6\1\0\1\6\1\0" +
                  "\1\64\3\6\1\64\3\6\42\0\1\65\2\0\1\66" +
                  "\1\67\1\0\1\65\3\0\1\65\1\0\1\67\50\0" +
                  "\1\70\45\0\1\6\2\0\1\51\1\52\1\13\1\61" +
                  "\1\6\1\62\1\63\1\0\1\13\1\71\1\6\1\72" +
                  "\1\13\1\6\1\63\1\6\35\0\1\6\2\0\1\51" +
                  "\1\52\1\73\2\6\1\0\1\6\1\0\1\73\3\6" +
                  "\1\73\3\6\62\0\1\74\1\0\1\75\60\0\1\76" +
                  "\1\0\1\77\55\0\1\100\61\0\1\101\61\0\1\102" +
                  "\60\0\1\103\67\0\1\104\15\0\1\105\54\0\1\106" +
                  "\4\0\3\106\1\0\1\106\1\0\10\106\35\0\1\53" +
                  "\4\0\3\53\1\0\1\53\1\0\10\53\35\0\1\53" +
                  "\1\107\3\0\3\53\1\0\1\53\1\0\10\53\31\0" +
                  "\4\110\1\53\4\110\3\53\1\110\1\53\1\110\10\53" +
                  "\31\110\4\0\1\111\2\0\1\112\1\113\3\111\1\0" +
                  "\1\111\1\0\10\111\35\0\1\6\2\0\1\51\1\52" +
                  "\2\61\1\6\1\62\1\63\1\0\1\61\3\6\1\61" +
                  "\1\6\1\63\1\6\42\0\1\114\3\0\1\67\1\0" +
                  "\1\114\3\0\1\114\1\0\1\67\36\0\1\6\1\115" +
                  "\1\0\1\51\1\52\2\116\1\6\1\0\1\6\1\115" +
                  "\1\116\3\6\1\116\3\6\35\0\1\6\2\0\1\51" +
                  "\1\52\2\64\1\6\1\117\1\6\1\0\1\64\3\6" +
                  "\1\64\3\6\42\0\2\65\2\0\1\67\1\0\1\65" +
                  "\3\0\1\65\1\0\1\67\46\0\1\120\50\0\1\115" +
                  "\3\0\2\121\3\0\1\115\1\121\3\0\1\121\40\0" +
                  "\1\6\2\0\1\51\1\52\2\122\1\6\1\0\1\122" +
                  "\1\0\1\122\1\6\3\122\1\6\2\122\35\0\1\6" +
                  "\2\0\1\51\1\52\1\6\1\123\1\6\1\0\1\6" +
                  "\1\0\1\123\3\6\1\123\3\6\31\0\4\124\1\6" +
                  "\2\124\1\125\1\126\1\127\1\130\1\6\1\124\1\6" +
                  "\1\124\1\127\3\6\1\127\3\6\31\124\3\0\1\131" +
                  "\1\132\3\0\1\133\3\132\1\0\1\132\1\0\10\132" +
                  "\1\134\1\135\63\0\1\136\27\0\1\106\2\0\1\51" +
                  "\1\52\3\106\1\0\1\106\1\0\10\106\35\0\1\111" +
                  "\2\0\1\51\1\52\3\111\1\0\1\111\1\0\10\111" +
                  "\35\0\1\111\4\0\3\111\1\0\1\111\1\0\10\111" +
                  "\42\0\2\114\1\0\1\117\1\67\1\0\1\114\3\0" +
                  "\1\114\1\0\1\67\43\0\2\121\4\0\1\121\3\0" +
                  "\1\121\40\0\1\6\2\0\1\51\1\52\2\116\1\6" +
                  "\1\0\1\6\1\0\1\116\3\6\1\116\3\6\42\0" +
                  "\1\137\5\0\1\137\3\0\1\137\37\0\1\131\4\0" +
                  "\1\133\16\0\1\134\1\135\33\0\1\132\4\0\3\132" +
                  "\1\0\1\132\1\0\10\132\31\0\1\133\2\0\5\133" +
                  "\1\140\47\133\1\134\2\0\24\134\1\140\30\134\1\135" +
                  "\2\0\25\135\1\140\27\135\11\0\2\137\1\0\1\117" +
                  "\2\0\1\137\3\0\1\137\34\0";
  /**
   * The transition table of the DFA
   */
  private static final int[] ZZ_TRANS = zzUnpackTrans();
  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;
  private static final char[] EMPTY_BUFFER = new char[0];
  private static final int YYEOF = -1;
  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
          "Unkown internal scanner error",
          "Error: could not match input",
          "Error: pushback value was too large"
  };
  private static final String ZZ_ATTRIBUTE_PACKED_0 =
          "\1\0\1\11\1\1\1\11\5\1\1\11\6\1\2\11" +
                  "\1\1\1\11\2\1\2\11\3\1\6\11\1\1\6\11" +
                  "\2\0\3\1\1\11\10\1\1\0\1\11\4\1\1\0" +
                  "\7\11\2\1\1\0\1\11\2\0\2\1\1\0\1\1" +
                  "\1\0\1\11\3\1\1\11\4\1\1\0\1\1\3\0" +
                  "\1\11\1\1\1\11";
  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int[] ZZ_ATTRIBUTE = zzUnpackAttribute();
  private static java.io.Reader zzReader = null; // Fake
  protected int trenarCounter = 0;
  /** the current state of the DFA */
  private int zzState;
  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;
  /** this buffer contains the current text to be matched and is
   the source of the yytext() string */
  private CharSequence zzBuffer = "";
  /** this buffer may contains the current text array to be matched when it is cheap to acquire it */
  private char[] zzBufferArray;
  /** the textposition at the last accepting state */
  private int zzMarkedPos;
  /** the textposition at the last state to be included in yytext */
  private int zzPushbackPos;
  /** the current text position in the buffer */
  private int zzCurrentPos;
  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;
  /** endRead marks the last character in the buffer, that has been read
   from input */
  private int zzEndRead;
  /**
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;
  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  public PerlLexerGenerated(java.io.Reader in) {
    this.zzReader = in;
  }

  /**
   * Creates a new scanner.
   * There is also java.io.Reader version of this constructor.
   *
   * @param   in  the java.io.Inputstream to read input from.
   */
  public PerlLexerGenerated(java.io.InputStream in) {
    this(new java.io.InputStreamReader(in));
  }

  private static int[] zzUnpackAction()
  {
    int[] result = new int[96];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int[] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  private static int[] zzUnpackRowMap()
  {
    int[] result = new int[96];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int[] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  private static int[] zzUnpackTrans()
  {
    int[] result = new int[2496];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int[] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  private static int[] zzUnpackAttribute()
  {
    int[] result = new int[96];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int[] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /**
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return the unpacked character translation table
   */
  private static char[] zzUnpackCMap(String packed)
  {
    char[] map = new char[0x10000];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < 148)
    {
      int count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }

  public CharSequence getBuffer()
  {
    return zzBuffer;
  }

  public char[] getBufferArray()
  {
    return zzBufferArray;
  }

  public int getBufferEnd()
  {
    return zzEndRead;}

    public int getNextTokenStart(){ return zzMarkedPos;}

    public boolean isLastToken(){ return zzMarkedPos == zzEndRead; }

	public abstract IElementType guessDiv();

	public abstract IElementType getIdentifierToken();

	public abstract IElementType checkOperatorXSticked();

  public abstract IElementType parseVersion();

  public abstract IElementType parseNumber();

  public abstract IElementType parseOperatorDereference();

  public abstract IElementType parseCappedVariableName();

  public abstract IElementType parseHeredocOpener();

  public abstract IElementType guessLtNumeric();

  public final int getTokenStart(){
    return zzStartRead;
  }

  /* user code: */
  // fixme this must be in skeleton
  public void setTokenStart(int position)
  {
    zzCurrentPos = zzStartRead = position;}

  public final int getTokenEnd(){
    return getTokenStart() + yylength();
  }

  public void setTokenEnd(int position)
  {
    zzMarkedPos = position;
  }

  public void reset(CharSequence buffer, int start, int end, int initialState){
    zzBuffer = buffer;
    zzBufferArray = com.intellij.util.text.CharArrayUtil.fromSequenceWithoutCopying(buffer);
    zzCurrentPos = zzMarkedPos = zzStartRead = start;
    zzPushbackPos = 0;
    zzAtEOF  = false;
    zzAtBOL = true;
    zzEndRead = end;
    yybegin(initialState);
  }

  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   *
   * @exception java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {
    return true;
  }


  /**
   * Returns the current lexical state.
   */
  public int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final CharSequence yytext() {
    return zzBuffer.subSequence(zzStartRead, zzMarkedPos);
  }


  /**
   * Returns the character at position <tt>pos</tt> from the
   * matched text.
   *
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch.
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos)
  {
    return zzBufferArray != null ? zzBufferArray[zzStartRead + pos] : zzBuffer.charAt(zzStartRead+pos);
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength()
  {
    return zzMarkedPos -zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of
   * yypushback(int) and a match-all fallback rule) this method
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    } catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  }


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)
  {
    if (number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return the next token
   * @exception java.io.IOException  if any I/O-Error occurs
   */
  public IElementType perlAdvance() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    CharSequence zzBufferL = zzBuffer;
    char[] zzBufferArrayL = zzBufferArray;
    char[] zzCMapL = ZZ_CMAP;

    int[] zzTransL = ZZ_TRANS;
    int[] zzRowMapL = ZZ_ROWMAP;
    int[] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;

      zzState = ZZ_LEXSTATE[zzLexicalState];


      zzForAction: {
        while (true) {

          if (zzCurrentPosL < zzEndReadL)
            zzInput = (zzBufferArrayL != null ? zzBufferArrayL[zzCurrentPosL++] : zzBufferL.charAt(zzCurrentPosL++));
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos = zzCurrentPosL;
            zzMarkedPos = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL = zzCurrentPos;
            zzMarkedPosL = zzMarkedPos;
            zzBufferL = zzBuffer;
            zzEndReadL = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = (zzBufferArrayL != null ? zzBufferArrayL[zzCurrentPosL++] : zzBufferL.charAt(zzCurrentPosL++));
            }
          }
          int zzNext = zzTransL[zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          int zzAttributes = zzAttrL[zzState];
          if ((zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ((zzAttributes & 8) == 8) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction])
      {
        case 9:
        {
          return NUMBER_SIMPLE;
        }
        case 59:
          break;
        case 37:
        {
          return parseOperatorDereference();
        }
        case 60:
          break;
        case 46:
        {
          return OPERATOR_RE;
        }
        case 61:
          break;
        case 32:
        {
          return LEFT_BRACKET;
        }
        case 62:
          break;
        case 49:
        {
          return OPERATOR_AND;
        }
        case 63:
          break;
        case 20:
        {
          return OPERATOR_NOT;
        }
        case 64:
          break;
        case 10:
        {
          return OPERATOR_CONCAT;
        }
        case 65:
          break;
        case 15:
        {
          return SEMICOLON;
        }
        case 66:
          break;
        case 38:
        {
          return parseCappedVariableName();
        }
        case 67:
          break;
        case 50:
        {
          return OPERATOR_OR;
        }
        case 68:
          break;
        case 18:
        {
          return OPERATOR_COMMA;
        }
        case 69:
          break;
        case 54:
        {
          yypushback(1);
          return OPERATOR_FILETEST;
        }
        case 70:
          break;
        case 44:
        {
          return OPERATOR_SHIFT_LEFT;
        }
        case 71:
          break;
        case 3:
        {
          return TokenType.WHITE_SPACE;
        }
        case 72:
          break;
        case 47:
        {
          return OPERATOR_SHIFT_RIGHT;
        }
        case 73:
          break;
        case 6:
        {
          return OPERATOR_BITWISE_XOR;
        }
        case 74:
          break;
        case 39:
        {
          return PACKAGE_IDENTIFIER;
        }
        case 75:
          break;
        case 51:
        {
          return SIGIL_SCALAR_INDEX;
        }
        case 76:
          break;
        case 34:
        {
          return LEFT_PAREN;
        }
        case 77:
          break;
        case 24:
        {
          return OPERATOR_REFERENCE;
        }
        case 78:
          break;
        case 43:
        {
          return OPERATOR_PLUS_PLUS;
        }
        case 79:
          break;
        case 2:
        {
          return TokenType.NEW_LINE_INDENT;
        }
        case 80:
          break;
        case 14:
        {
          return guessLtNumeric();
        }
        case 81:
          break;
        case 7:
        {
          return COLON;
        }
        case 82:
          break;
        case 45:
        {
          return OPERATOR_COMMA_ARROW;
        }
        case 83:
          break;
        case 28:
        {
          return SIGIL_ARRAY;
        }
        case 84:
          break;
        case 21:
        {
          return OPERATOR_BITWISE_AND;
        }
        case 85:
          break;
        case 19:
        {
          return OPERATOR_BITWISE_NOT;
        }
        case 86:
          break;
        case 16:
        {
          return OPERATOR_ASSIGN;
        }
        case 87:
          break;
        case 5:
        {
          return OPERATOR_MINUS;
        }
        case 88:
          break;
        case 56:
        {
          return checkOperatorXSticked();
        }
        case 89:
          break;
        case 55:
        {
          return OPERATOR_HELLIP;
        }
        case 90:
          break;
        case 26:
        {
          return OPERATOR_MUL;
        }
        case 91:
          break;
        case 29:
        {
          return SIGIL_SCALAR;
        }
        case 92:
          break;
        case 57:
        {
          return parseHeredocOpener();
        }
        case 93:
          break;
        case 30:
        {
          return LEFT_BRACE;
        }
        case 94:
          break;
        case 17:
        {
          return OPERATOR_GT_NUMERIC;
        }
        case 95:
          break;
        case 41:
        {
          return parseVersion();
        }
        case 96:
          break;
        case 1:
        {
          return TokenType.BAD_CHARACTER;
        }
        case 97:
          break;
        case 13:
        {
          return QUOTE_TICK;
        }
        case 98:
          break;
        case 35:
        {
          return RIGHT_PAREN;
        }
        case 99:
          break;
        case 42:
        {
          return OPERATOR_FLIP_FLOP;
        }
        case 100:
          break;
        case 33:
        {
          return RIGHT_BRACKET;
        }
        case 101:
          break;
        case 25:
        {
          return guessDiv();
        }
        case 102:
          break;
        case 53:
        {
          return parsePackage();
        }
        case 103:
          break;
        case 58:
        {
          return OPERATOR_CMP_NUMERIC;
        }
        case 104:
          break;
        case 27:
        {
          return OPERATOR_MOD;
        }
        case 105:
          break;
        case 8:
        {
          return QUOTE_SINGLE;
        }
        case 106:
          break;
        case 40:
        {
          return parseNumber();
        }
        case 107:
          break;
        case 52:
        {
          return parsePackageCanonical();
        }
        case 108:
          break;
        case 48:
        {
          return OPERATOR_NOT_RE;
        }
        case 109:
          break;
        case 36:
        {
          return OPERATOR_MINUS_MINUS;
        }
        case 110:
          break;
        case 23:
        {
          return QUESTION;
        }
        case 111:
          break;
        case 11:
        {
          return OPERATOR_PLUS;
        }
        case 112:
          break;
        case 4:
        {
          return parseBarewordMinus();
        }
        case 113:
          break;
        case 12:
        {
          return QUOTE_DOUBLE;
        }
        case 114:
          break;
        case 31:
        {
          return RIGHT_BRACE;
        }
        case 115:
          break;
        case 22:
        {
          return OPERATOR_BITWISE_OR;
          }
        case 116: break;
        default:
          if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
            zzAtEOF = true;
            return null;
          }
          else {
            zzScanError(ZZ_NO_MATCH);
          }
      }
    }
  }


}
