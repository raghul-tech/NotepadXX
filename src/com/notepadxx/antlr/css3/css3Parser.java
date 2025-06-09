package com.notepadxx.antlr.css3;
import java.util.List;

import org.antlr.v4.runtime.NoViableAltException;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.RuntimeMetaData;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.Vocabulary;
import org.antlr.v4.runtime.VocabularyImpl;
// Generated from CSS3/css3Parser.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.ParserATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.TerminalNode;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class css3Parser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		OpenBracket=1, CloseBracket=2, OpenParen=3, CloseParen=4, OpenBrace=5,
		CloseBrace=6, SemiColon=7, Equal=8, Colon=9, Dot=10, Multiply=11, Divide=12,
		Pipe=13, Underscore=14, Comment=15, Url=16, Space=17, Cdo=18, Cdc=19,
		Includes=20, DashMatch=21, Hash=22, Import=23, Page=24, Media=25, Namespace=26,
		Charset=27, Important=28, Percentage=29, Url_=30, UnicodeRange=31, MediaOnly=32,
		Not=33, And=34, Dimension=35, UnknownDimension=36, Plus=37, Minus=38,
		Greater=39, Comma=40, Tilde=41, PseudoNot=42, Number=43, String_=44, PrefixMatch=45,
		SuffixMatch=46, SubstringMatch=47, FontFace=48, Supports=49, Or=50, Keyframes=51,
		From=52, To=53, Calc=54, Viewport=55, CounterStyle=56, FontFeatureValues=57,
		DxImageTransform=58, AtKeyword=59, Variable=60, Var=61, Ident=62, Function_=63,
		UnexpectedCharacter=64;
	public static final int
		RULE_stylesheet = 0, RULE_charset = 1, RULE_imports = 2, RULE_namespace_ = 3,
		RULE_namespacePrefix = 4, RULE_media = 5, RULE_mediaQueryList = 6, RULE_mediaQuery = 7,
		RULE_mediaType = 8, RULE_mediaExpression = 9, RULE_mediaFeature = 10,
		RULE_page = 11, RULE_pseudoPage = 12, RULE_selectorGroup = 13, RULE_selector = 14,
		RULE_combinator = 15, RULE_simpleSelectorSequence = 16, RULE_typeSelector = 17,
		RULE_typeNamespacePrefix = 18, RULE_elementName = 19, RULE_universal = 20,
		RULE_className = 21, RULE_attrib = 22, RULE_pseudo = 23, RULE_functionalPseudo = 24,
		RULE_expression = 25, RULE_negation = 26, RULE_negationArg = 27, RULE_operator_ = 28,
		RULE_property_ = 29, RULE_ruleset = 30, RULE_declarationList = 31, RULE_declaration = 32,
		RULE_prio = 33, RULE_value = 34, RULE_expr = 35, RULE_term = 36, RULE_function_ = 37,
		RULE_dxImageTransform = 38, RULE_hexcolor = 39, RULE_number = 40, RULE_percentage = 41,
		RULE_dimension = 42, RULE_unknownDimension = 43, RULE_any_ = 44, RULE_atRule = 45,
		RULE_unused = 46, RULE_block = 47, RULE_nestedStatement = 48, RULE_groupRuleBody = 49,
		RULE_supportsRule = 50, RULE_supportsCondition = 51, RULE_supportsConditionInParens = 52,
		RULE_supportsNegation = 53, RULE_supportsConjunction = 54, RULE_supportsDisjunction = 55,
		RULE_supportsDeclarationCondition = 56, RULE_generalEnclosed = 57, RULE_url = 58,
		RULE_var_ = 59, RULE_calc = 60, RULE_calcSum = 61, RULE_calcProduct = 62,
		RULE_calcValue = 63, RULE_fontFaceRule = 64, RULE_fontFaceDeclaration = 65,
		RULE_keyframesRule = 66, RULE_keyframeBlock = 67, RULE_keyframeSelector = 68,
		RULE_viewport = 69, RULE_counterStyle = 70, RULE_fontFeatureValuesRule = 71,
		RULE_fontFamilyNameList = 72, RULE_fontFamilyName = 73, RULE_featureValueBlock = 74,
		RULE_featureType = 75, RULE_featureValueDefinition = 76, RULE_ident = 77,
		RULE_ws = 78;
	private static String[] makeRuleNames() {
		return new String[] {
			"stylesheet", "charset", "imports", "namespace_", "namespacePrefix",
			"media", "mediaQueryList", "mediaQuery", "mediaType", "mediaExpression",
			"mediaFeature", "page", "pseudoPage", "selectorGroup", "selector", "combinator",
			"simpleSelectorSequence", "typeSelector", "typeNamespacePrefix", "elementName",
			"universal", "className", "attrib", "pseudo", "functionalPseudo", "expression",
			"negation", "negationArg", "operator_", "property_", "ruleset", "declarationList",
			"declaration", "prio", "value", "expr", "term", "function_", "dxImageTransform",
			"hexcolor", "number", "percentage", "dimension", "unknownDimension",
			"any_", "atRule", "unused", "block", "nestedStatement", "groupRuleBody",
			"supportsRule", "supportsCondition", "supportsConditionInParens", "supportsNegation",
			"supportsConjunction", "supportsDisjunction", "supportsDeclarationCondition",
			"generalEnclosed", "url", "var_", "calc", "calcSum", "calcProduct", "calcValue",
			"fontFaceRule", "fontFaceDeclaration", "keyframesRule", "keyframeBlock",
			"keyframeSelector", "viewport", "counterStyle", "fontFeatureValuesRule",
			"fontFamilyNameList", "fontFamilyName", "featureValueBlock", "featureType",
			"featureValueDefinition", "ident", "ws"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'['", "']'", "'('", "')'", "'{'", "'}'", "';'", "'='", "':'",
			"'.'", "'*'", "'/'", "'|'", "'_'", null, null, null, "'<!--'", "'-->'",
			"'~='", "'|='", null, null, null, null, null, "'@charset '", null, null,
			"'url('", null, null, null, null, null, null, "'+'", "'-'", "'>'", "','",
			"'~'", null, null, null, "'^='", "'$='", "'*='", null, null, null, null,
			null, null, "'calc('", null, null, null, null, null, null, "'var('"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "OpenBracket", "CloseBracket", "OpenParen", "CloseParen", "OpenBrace",
			"CloseBrace", "SemiColon", "Equal", "Colon", "Dot", "Multiply", "Divide",
			"Pipe", "Underscore", "Comment", "Url", "Space", "Cdo", "Cdc", "Includes",
			"DashMatch", "Hash", "Import", "Page", "Media", "Namespace", "Charset",
			"Important", "Percentage", "Url_", "UnicodeRange", "MediaOnly", "Not",
			"And", "Dimension", "UnknownDimension", "Plus", "Minus", "Greater", "Comma",
			"Tilde", "PseudoNot", "Number", "String_", "PrefixMatch", "SuffixMatch",
			"SubstringMatch", "FontFace", "Supports", "Or", "Keyframes", "From",
			"To", "Calc", "Viewport", "CounterStyle", "FontFeatureValues", "DxImageTransform",
			"AtKeyword", "Variable", "Var", "Ident", "Function_", "UnexpectedCharacter"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "css3Parser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public css3Parser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StylesheetContext extends ParserRuleContext {
		public WsContext ws() {
			return getRuleContext(WsContext.class,0);
		}
		public TerminalNode EOF() { return getToken(Recognizer.EOF, 0); }
		public List<CharsetContext> charset() {
			return getRuleContexts(CharsetContext.class);
		}
		public CharsetContext charset(int i) {
			return getRuleContext(CharsetContext.class,i);
		}
		public List<ImportsContext> imports() {
			return getRuleContexts(ImportsContext.class);
		}
		public ImportsContext imports(int i) {
			return getRuleContext(ImportsContext.class,i);
		}
		public List<Namespace_Context> namespace_() {
			return getRuleContexts(Namespace_Context.class);
		}
		public Namespace_Context namespace_(int i) {
			return getRuleContext(Namespace_Context.class,i);
		}
		public List<NestedStatementContext> nestedStatement() {
			return getRuleContexts(NestedStatementContext.class);
		}
		public NestedStatementContext nestedStatement(int i) {
			return getRuleContext(NestedStatementContext.class,i);
		}
		public List<TerminalNode> Comment() { return getTokens(css3Parser.Comment); }
		public TerminalNode Comment(int i) {
			return getToken(css3Parser.Comment, i);
		}
		public List<TerminalNode> Space() { return getTokens(css3Parser.Space); }
		public TerminalNode Space(int i) {
			return getToken(css3Parser.Space, i);
		}
		public List<TerminalNode> Cdo() { return getTokens(css3Parser.Cdo); }
		public TerminalNode Cdo(int i) {
			return getToken(css3Parser.Cdo, i);
		}
		public List<TerminalNode> Cdc() { return getTokens(css3Parser.Cdc); }
		public TerminalNode Cdc(int i) {
			return getToken(css3Parser.Cdc, i);
		}
		public StylesheetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stylesheet; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).enterStylesheet(this);
			}
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).exitStylesheet(this);
			}
		}
	}

	public final StylesheetContext stylesheet() throws RecognitionException {
		StylesheetContext _localctx = new StylesheetContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_stylesheet);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(158);
			ws();
			setState(168);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Charset) {
				{
				{
				setState(159);
				charset();
				setState(163);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 950272L) != 0)) {
					{
					{
					setState(160);
					_la = _input.LA(1);
					if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 950272L) != 0)) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) {
							matchedEOF = true;
						}
						_errHandler.reportMatch(this);
						consume();
					}
					}
					}
					setState(165);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				}
				setState(170);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(180);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Import) {
				{
				{
				setState(171);
				imports();
				setState(175);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 950272L) != 0)) {
					{
					{
					setState(172);
					_la = _input.LA(1);
					if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 950272L) != 0)) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) {
							matchedEOF = true;
						}
						_errHandler.reportMatch(this);
						consume();
					}
					}
					}
					setState(177);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				}
				setState(182);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(192);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Namespace) {
				{
				{
				setState(183);
				namespace_();
				setState(187);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 950272L) != 0)) {
					{
					{
					setState(184);
					_la = _input.LA(1);
					if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 950272L) != 0)) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) {
							matchedEOF = true;
						}
						_errHandler.reportMatch(this);
						consume();
					}
					}
					}
					setState(189);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				}
				setState(194);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(204);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & -3765259427856175574L) != 0)) {
				{
				{
				setState(195);
				nestedStatement();
				setState(199);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 950272L) != 0)) {
					{
					{
					setState(196);
					_la = _input.LA(1);
					if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 950272L) != 0)) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) {
							matchedEOF = true;
						}
						_errHandler.reportMatch(this);
						consume();
					}
					}
					}
					setState(201);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				}
				setState(206);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(207);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CharsetContext extends ParserRuleContext {
		public CharsetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_charset; }

		public CharsetContext() { }
		public void copyFrom(CharsetContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class BadCharsetContext extends CharsetContext {
		public TerminalNode Charset() { return getToken(css3Parser.Charset, 0); }
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public TerminalNode String_() { return getToken(css3Parser.String_, 0); }
		public BadCharsetContext(CharsetContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).enterBadCharset(this);
			}
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).exitBadCharset(this);
			}
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class GoodCharsetContext extends CharsetContext {
		public TerminalNode Charset() { return getToken(css3Parser.Charset, 0); }
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public TerminalNode String_() { return getToken(css3Parser.String_, 0); }
		public TerminalNode SemiColon() { return getToken(css3Parser.SemiColon, 0); }
		public GoodCharsetContext(CharsetContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).enterGoodCharset(this);
			}
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).exitGoodCharset(this);
			}
		}
	}

	public final CharsetContext charset() throws RecognitionException {
		CharsetContext _localctx = new CharsetContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_charset);
		try {
			setState(221);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				_localctx = new GoodCharsetContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(209);
				match(Charset);
				setState(210);
				ws();
				setState(211);
				match(String_);
				setState(212);
				ws();
				setState(213);
				match(SemiColon);
				setState(214);
				ws();
				}
				break;
			case 2:
				_localctx = new BadCharsetContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(216);
				match(Charset);
				setState(217);
				ws();
				setState(218);
				match(String_);
				setState(219);
				ws();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ImportsContext extends ParserRuleContext {
		public ImportsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_imports; }

		public ImportsContext() { }
		public void copyFrom(ImportsContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class BadImportContext extends ImportsContext {
		public TerminalNode Import() { return getToken(css3Parser.Import, 0); }
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public MediaQueryListContext mediaQueryList() {
			return getRuleContext(MediaQueryListContext.class,0);
		}
		public TerminalNode String_() { return getToken(css3Parser.String_, 0); }
		public UrlContext url() {
			return getRuleContext(UrlContext.class,0);
		}
		public BadImportContext(ImportsContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).enterBadImport(this);
			}
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).exitBadImport(this);
			}
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class GoodImportContext extends ImportsContext {
		public TerminalNode Import() { return getToken(css3Parser.Import, 0); }
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public MediaQueryListContext mediaQueryList() {
			return getRuleContext(MediaQueryListContext.class,0);
		}
		public TerminalNode SemiColon() { return getToken(css3Parser.SemiColon, 0); }
		public TerminalNode String_() { return getToken(css3Parser.String_, 0); }
		public UrlContext url() {
			return getRuleContext(UrlContext.class,0);
		}
		public GoodImportContext(ImportsContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).enterGoodImport(this);
			}
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).exitGoodImport(this);
			}
		}
	}

	public final ImportsContext imports() throws RecognitionException {
		ImportsContext _localctx = new ImportsContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_imports);
		try {
			setState(261);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				_localctx = new GoodImportContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(223);
				match(Import);
				setState(224);
				ws();
				setState(227);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case String_:
					{
					setState(225);
					match(String_);
					}
					break;
				case Url:
				case Url_:
					{
					setState(226);
					url();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(229);
				ws();
				setState(230);
				mediaQueryList();
				setState(231);
				match(SemiColon);
				setState(232);
				ws();
				}
				break;
			case 2:
				_localctx = new GoodImportContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(234);
				match(Import);
				setState(235);
				ws();
				setState(238);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case String_:
					{
					setState(236);
					match(String_);
					}
					break;
				case Url:
				case Url_:
					{
					setState(237);
					url();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(240);
				ws();
				setState(241);
				match(SemiColon);
				setState(242);
				ws();
				}
				break;
			case 3:
				_localctx = new BadImportContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(244);
				match(Import);
				setState(245);
				ws();
				setState(248);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case String_:
					{
					setState(246);
					match(String_);
					}
					break;
				case Url:
				case Url_:
					{
					setState(247);
					url();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(250);
				ws();
				setState(251);
				mediaQueryList();
				}
				break;
			case 4:
				_localctx = new BadImportContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(253);
				match(Import);
				setState(254);
				ws();
				setState(257);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case String_:
					{
					setState(255);
					match(String_);
					}
					break;
				case Url:
				case Url_:
					{
					setState(256);
					url();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(259);
				ws();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Namespace_Context extends ParserRuleContext {
		public Namespace_Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_namespace_; }

		public Namespace_Context() { }
		public void copyFrom(Namespace_Context ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class GoodNamespaceContext extends Namespace_Context {
		public TerminalNode Namespace() { return getToken(css3Parser.Namespace, 0); }
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public TerminalNode SemiColon() { return getToken(css3Parser.SemiColon, 0); }
		public TerminalNode String_() { return getToken(css3Parser.String_, 0); }
		public UrlContext url() {
			return getRuleContext(UrlContext.class,0);
		}
		public NamespacePrefixContext namespacePrefix() {
			return getRuleContext(NamespacePrefixContext.class,0);
		}
		public GoodNamespaceContext(Namespace_Context ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).enterGoodNamespace(this);
			}
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).exitGoodNamespace(this);
			}
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class BadNamespaceContext extends Namespace_Context {
		public TerminalNode Namespace() { return getToken(css3Parser.Namespace, 0); }
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public TerminalNode String_() { return getToken(css3Parser.String_, 0); }
		public UrlContext url() {
			return getRuleContext(UrlContext.class,0);
		}
		public NamespacePrefixContext namespacePrefix() {
			return getRuleContext(NamespacePrefixContext.class,0);
		}
		public BadNamespaceContext(Namespace_Context ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).enterBadNamespace(this);
			}
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).exitBadNamespace(this);
			}
		}
	}

	public final Namespace_Context namespace_() throws RecognitionException {
		Namespace_Context _localctx = new Namespace_Context(_ctx, getState());
		enterRule(_localctx, 6, RULE_namespace_);
		int _la;
		try {
			setState(291);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
			case 1:
				_localctx = new GoodNamespaceContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(263);
				match(Namespace);
				setState(264);
				ws();
				setState(268);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 4626322747281113088L) != 0)) {
					{
					setState(265);
					namespacePrefix();
					setState(266);
					ws();
					}
				}

				setState(272);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case String_:
					{
					setState(270);
					match(String_);
					}
					break;
				case Url:
				case Url_:
					{
					setState(271);
					url();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(274);
				ws();
				setState(275);
				match(SemiColon);
				setState(276);
				ws();
				}
				break;
			case 2:
				_localctx = new BadNamespaceContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(278);
				match(Namespace);
				setState(279);
				ws();
				setState(283);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 4626322747281113088L) != 0)) {
					{
					setState(280);
					namespacePrefix();
					setState(281);
					ws();
					}
				}

				setState(287);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case String_:
					{
					setState(285);
					match(String_);
					}
					break;
				case Url:
				case Url_:
					{
					setState(286);
					url();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(289);
				ws();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class NamespacePrefixContext extends ParserRuleContext {
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public NamespacePrefixContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_namespacePrefix; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).enterNamespacePrefix(this);
			}
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).exitNamespacePrefix(this);
			}
		}
	}

	public final NamespacePrefixContext namespacePrefix() throws RecognitionException {
		NamespacePrefixContext _localctx = new NamespacePrefixContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_namespacePrefix);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(293);
			ident();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MediaContext extends ParserRuleContext {
		public TerminalNode Media() { return getToken(css3Parser.Media, 0); }
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public MediaQueryListContext mediaQueryList() {
			return getRuleContext(MediaQueryListContext.class,0);
		}
		public GroupRuleBodyContext groupRuleBody() {
			return getRuleContext(GroupRuleBodyContext.class,0);
		}
		public MediaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_media; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).enterMedia(this);
			}
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).exitMedia(this);
			}
		}
	}

	public final MediaContext media() throws RecognitionException {
		MediaContext _localctx = new MediaContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_media);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(295);
			match(Media);
			setState(296);
			ws();
			setState(297);
			mediaQueryList();
			setState(298);
			groupRuleBody();
			setState(299);
			ws();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MediaQueryListContext extends ParserRuleContext {
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public List<MediaQueryContext> mediaQuery() {
			return getRuleContexts(MediaQueryContext.class);
		}
		public MediaQueryContext mediaQuery(int i) {
			return getRuleContext(MediaQueryContext.class,i);
		}
		public List<TerminalNode> Comma() { return getTokens(css3Parser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(css3Parser.Comma, i);
		}
		public MediaQueryListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mediaQueryList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).enterMediaQueryList(this);
			}
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).exitMediaQueryList(this);
			}
		}
	}

	public final MediaQueryListContext mediaQueryList() throws RecognitionException {
		MediaQueryListContext _localctx = new MediaQueryListContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_mediaQueryList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(311);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
			case 1:
				{
				setState(301);
				mediaQuery();
				setState(308);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Comma) {
					{
					{
					setState(302);
					match(Comma);
					setState(303);
					ws();
					setState(304);
					mediaQuery();
					}
					}
					setState(310);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			}
			setState(313);
			ws();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MediaQueryContext extends ParserRuleContext {
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public MediaTypeContext mediaType() {
			return getRuleContext(MediaTypeContext.class,0);
		}
		public List<TerminalNode> And() { return getTokens(css3Parser.And); }
		public TerminalNode And(int i) {
			return getToken(css3Parser.And, i);
		}
		public List<MediaExpressionContext> mediaExpression() {
			return getRuleContexts(MediaExpressionContext.class);
		}
		public MediaExpressionContext mediaExpression(int i) {
			return getRuleContext(MediaExpressionContext.class,i);
		}
		public TerminalNode MediaOnly() { return getToken(css3Parser.MediaOnly, 0); }
		public TerminalNode Not() { return getToken(css3Parser.Not, 0); }
		public MediaQueryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mediaQuery; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).enterMediaQuery(this);
			}
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).exitMediaQuery(this);
			}
		}
	}

	public final MediaQueryContext mediaQuery() throws RecognitionException {
		MediaQueryContext _localctx = new MediaQueryContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_mediaQuery);
		int _la;
		try {
			int _alt;
			setState(340);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Comment:
			case Space:
			case MediaOnly:
			case Not:
			case And:
			case Or:
			case From:
			case To:
			case Ident:
				enterOuterAlt(_localctx, 1);
				{
				setState(316);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
				case 1:
					{
					setState(315);
					_la = _input.LA(1);
					if ( !(_la==MediaOnly || _la==Not) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) {
							matchedEOF = true;
						}
						_errHandler.reportMatch(this);
						consume();
					}
					}
					break;
				}
				setState(318);
				ws();
				setState(319);
				mediaType();
				setState(320);
				ws();
				setState(327);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,22,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(321);
						match(And);
						setState(322);
						ws();
						setState(323);
						mediaExpression();
						}
						}
					}
					setState(329);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,22,_ctx);
				}
				}
				break;
			case OpenParen:
				enterOuterAlt(_localctx, 2);
				{
				setState(330);
				mediaExpression();
				setState(337);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(331);
						match(And);
						setState(332);
						ws();
						setState(333);
						mediaExpression();
						}
						}
					}
					setState(339);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MediaTypeContext extends ParserRuleContext {
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public MediaTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mediaType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).enterMediaType(this);
			}
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).exitMediaType(this);
			}
		}
	}

	public final MediaTypeContext mediaType() throws RecognitionException {
		MediaTypeContext _localctx = new MediaTypeContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_mediaType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(342);
			ident();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MediaExpressionContext extends ParserRuleContext {
		public TerminalNode OpenParen() { return getToken(css3Parser.OpenParen, 0); }
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public MediaFeatureContext mediaFeature() {
			return getRuleContext(MediaFeatureContext.class,0);
		}
		public TerminalNode CloseParen() { return getToken(css3Parser.CloseParen, 0); }
		public TerminalNode Colon() { return getToken(css3Parser.Colon, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public MediaExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mediaExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).enterMediaExpression(this);
			}
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).exitMediaExpression(this);
			}
		}
	}

	public final MediaExpressionContext mediaExpression() throws RecognitionException {
		MediaExpressionContext _localctx = new MediaExpressionContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_mediaExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(344);
			match(OpenParen);
			setState(345);
			ws();
			setState(346);
			mediaFeature();
			setState(351);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Colon) {
				{
				setState(347);
				match(Colon);
				setState(348);
				ws();
				setState(349);
				expr();
				}
			}

			setState(353);
			match(CloseParen);
			setState(354);
			ws();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MediaFeatureContext extends ParserRuleContext {
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public WsContext ws() {
			return getRuleContext(WsContext.class,0);
		}
		public MediaFeatureContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mediaFeature; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).enterMediaFeature(this);
			}
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).exitMediaFeature(this);
			}
		}
	}

	public final MediaFeatureContext mediaFeature() throws RecognitionException {
		MediaFeatureContext _localctx = new MediaFeatureContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_mediaFeature);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(356);
			ident();
			setState(357);
			ws();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PageContext extends ParserRuleContext {
		public TerminalNode Page() { return getToken(css3Parser.Page, 0); }
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public TerminalNode OpenBrace() { return getToken(css3Parser.OpenBrace, 0); }
		public TerminalNode CloseBrace() { return getToken(css3Parser.CloseBrace, 0); }
		public PseudoPageContext pseudoPage() {
			return getRuleContext(PseudoPageContext.class,0);
		}
		public List<DeclarationContext> declaration() {
			return getRuleContexts(DeclarationContext.class);
		}
		public DeclarationContext declaration(int i) {
			return getRuleContext(DeclarationContext.class,i);
		}
		public List<TerminalNode> SemiColon() { return getTokens(css3Parser.SemiColon); }
		public TerminalNode SemiColon(int i) {
			return getToken(css3Parser.SemiColon, i);
		}
		public PageContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_page; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).enterPage(this);
			}
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).exitPage(this);
			}
		}
	}

	public final PageContext page() throws RecognitionException {
		PageContext _localctx = new PageContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_page);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(359);
			match(Page);
			setState(360);
			ws();
			setState(362);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Colon) {
				{
				setState(361);
				pseudoPage();
				}
			}

			setState(364);
			match(OpenBrace);
			setState(365);
			ws();
			setState(367);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 5779244251887978496L) != 0)) {
				{
				setState(366);
				declaration();
				}
			}

			setState(376);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SemiColon) {
				{
				{
				setState(369);
				match(SemiColon);
				setState(370);
				ws();
				setState(372);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 5779244251887978496L) != 0)) {
					{
					setState(371);
					declaration();
					}
				}

				}
				}
				setState(378);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(379);
			match(CloseBrace);
			setState(380);
			ws();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PseudoPageContext extends ParserRuleContext {
		public TerminalNode Colon() { return getToken(css3Parser.Colon, 0); }
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public WsContext ws() {
			return getRuleContext(WsContext.class,0);
		}
		public PseudoPageContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pseudoPage; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).enterPseudoPage(this);
			}
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).exitPseudoPage(this);
			}
		}
	}

	public final PseudoPageContext pseudoPage() throws RecognitionException {
		PseudoPageContext _localctx = new PseudoPageContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_pseudoPage);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(382);
			match(Colon);
			setState(383);
			ident();
			setState(384);
			ws();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SelectorGroupContext extends ParserRuleContext {
		public List<SelectorContext> selector() {
			return getRuleContexts(SelectorContext.class);
		}
		public SelectorContext selector(int i) {
			return getRuleContext(SelectorContext.class,i);
		}
		public List<TerminalNode> Comma() { return getTokens(css3Parser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(css3Parser.Comma, i);
		}
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public SelectorGroupContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_selectorGroup; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).enterSelectorGroup(this);
			}
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).exitSelectorGroup(this);
			}
		}
	}

	public final SelectorGroupContext selectorGroup() throws RecognitionException {
		SelectorGroupContext _localctx = new SelectorGroupContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_selectorGroup);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(386);
			selector();
			setState(393);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Comma) {
				{
				{
				setState(387);
				match(Comma);
				setState(388);
				ws();
				setState(389);
				selector();
				}
				}
				setState(395);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SelectorContext extends ParserRuleContext {
		public List<SimpleSelectorSequenceContext> simpleSelectorSequence() {
			return getRuleContexts(SimpleSelectorSequenceContext.class);
		}
		public SimpleSelectorSequenceContext simpleSelectorSequence(int i) {
			return getRuleContext(SimpleSelectorSequenceContext.class,i);
		}
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public List<CombinatorContext> combinator() {
			return getRuleContexts(CombinatorContext.class);
		}
		public CombinatorContext combinator(int i) {
			return getRuleContext(CombinatorContext.class,i);
		}
		public SelectorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_selector; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).enterSelector(this);
			}
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).exitSelector(this);
			}
		}
	}

	public final SelectorContext selector() throws RecognitionException {
		SelectorContext _localctx = new SelectorContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_selector);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(396);
			simpleSelectorSequence();
			setState(397);
			ws();
			setState(404);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 2886218153984L) != 0)) {
				{
				{
				setState(398);
				combinator();
				setState(399);
				simpleSelectorSequence();
				setState(400);
				ws();
				}
				}
				setState(406);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CombinatorContext extends ParserRuleContext {
		public TerminalNode Plus() { return getToken(css3Parser.Plus, 0); }
		public WsContext ws() {
			return getRuleContext(WsContext.class,0);
		}
		public TerminalNode Greater() { return getToken(css3Parser.Greater, 0); }
		public TerminalNode Tilde() { return getToken(css3Parser.Tilde, 0); }
		public TerminalNode Space() { return getToken(css3Parser.Space, 0); }
		public CombinatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_combinator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).enterCombinator(this);
			}
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).exitCombinator(this);
			}
		}
	}

	public final CombinatorContext combinator() throws RecognitionException {
		CombinatorContext _localctx = new CombinatorContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_combinator);
		try {
			setState(415);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Plus:
				enterOuterAlt(_localctx, 1);
				{
				setState(407);
				match(Plus);
				setState(408);
				ws();
				}
				break;
			case Greater:
				enterOuterAlt(_localctx, 2);
				{
				setState(409);
				match(Greater);
				setState(410);
				ws();
				}
				break;
			case Tilde:
				enterOuterAlt(_localctx, 3);
				{
				setState(411);
				match(Tilde);
				setState(412);
				ws();
				}
				break;
			case Space:
				enterOuterAlt(_localctx, 4);
				{
				setState(413);
				match(Space);
				setState(414);
				ws();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SimpleSelectorSequenceContext extends ParserRuleContext {
		public TypeSelectorContext typeSelector() {
			return getRuleContext(TypeSelectorContext.class,0);
		}
		public UniversalContext universal() {
			return getRuleContext(UniversalContext.class,0);
		}
		public List<TerminalNode> Hash() { return getTokens(css3Parser.Hash); }
		public TerminalNode Hash(int i) {
			return getToken(css3Parser.Hash, i);
		}
		public List<ClassNameContext> className() {
			return getRuleContexts(ClassNameContext.class);
		}
		public ClassNameContext className(int i) {
			return getRuleContext(ClassNameContext.class,i);
		}
		public List<AttribContext> attrib() {
			return getRuleContexts(AttribContext.class);
		}
		public AttribContext attrib(int i) {
			return getRuleContext(AttribContext.class,i);
		}
		public List<PseudoContext> pseudo() {
			return getRuleContexts(PseudoContext.class);
		}
		public PseudoContext pseudo(int i) {
			return getRuleContext(PseudoContext.class,i);
		}
		public List<NegationContext> negation() {
			return getRuleContexts(NegationContext.class);
		}
		public NegationContext negation(int i) {
			return getRuleContext(NegationContext.class,i);
		}
		public SimpleSelectorSequenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_simpleSelectorSequence; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).enterSimpleSelectorSequence(this);
			}
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).exitSimpleSelectorSequence(this);
			}
		}
	}

	public final SimpleSelectorSequenceContext simpleSelectorSequence() throws RecognitionException {
		SimpleSelectorSequenceContext _localctx = new SimpleSelectorSequenceContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_simpleSelectorSequence);
		int _la;
		try {
			setState(440);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Multiply:
			case Pipe:
			case MediaOnly:
			case Not:
			case And:
			case Or:
			case From:
			case To:
			case Ident:
				enterOuterAlt(_localctx, 1);
				{
				setState(419);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,33,_ctx) ) {
				case 1:
					{
					setState(417);
					typeSelector();
					}
					break;
				case 2:
					{
					setState(418);
					universal();
					}
					break;
				}
				setState(428);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 4398050706946L) != 0)) {
					{
					setState(426);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case Hash:
						{
						setState(421);
						match(Hash);
						}
						break;
					case Dot:
						{
						setState(422);
						className();
						}
						break;
					case OpenBracket:
						{
						setState(423);
						attrib();
						}
						break;
					case Colon:
						{
						setState(424);
						pseudo();
						}
						break;
					case PseudoNot:
						{
						setState(425);
						negation();
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					}
					setState(430);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case OpenBracket:
			case Colon:
			case Dot:
			case Hash:
			case PseudoNot:
				enterOuterAlt(_localctx, 2);
				{
				setState(436);
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					setState(436);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case Hash:
						{
						setState(431);
						match(Hash);
						}
						break;
					case Dot:
						{
						setState(432);
						className();
						}
						break;
					case OpenBracket:
						{
						setState(433);
						attrib();
						}
						break;
					case Colon:
						{
						setState(434);
						pseudo();
						}
						break;
					case PseudoNot:
						{
						setState(435);
						negation();
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					}
					setState(438);
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 4398050706946L) != 0) );
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TypeSelectorContext extends ParserRuleContext {
		public ElementNameContext elementName() {
			return getRuleContext(ElementNameContext.class,0);
		}
		public TypeNamespacePrefixContext typeNamespacePrefix() {
			return getRuleContext(TypeNamespacePrefixContext.class,0);
		}
		public TypeSelectorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeSelector; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).enterTypeSelector(this);
			}
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).exitTypeSelector(this);
			}
		}
	}

	public final TypeSelectorContext typeSelector() throws RecognitionException {
		TypeSelectorContext _localctx = new TypeSelectorContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_typeSelector);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(443);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,39,_ctx) ) {
			case 1:
				{
				setState(442);
				typeNamespacePrefix();
				}
				break;
			}
			setState(445);
			elementName();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TypeNamespacePrefixContext extends ParserRuleContext {
		public TerminalNode Pipe() { return getToken(css3Parser.Pipe, 0); }
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public TerminalNode Multiply() { return getToken(css3Parser.Multiply, 0); }
		public TypeNamespacePrefixContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeNamespacePrefix; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).enterTypeNamespacePrefix(this);
			}
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).exitTypeNamespacePrefix(this);
			}
		}
	}

	public final TypeNamespacePrefixContext typeNamespacePrefix() throws RecognitionException {
		TypeNamespacePrefixContext _localctx = new TypeNamespacePrefixContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_typeNamespacePrefix);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(449);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case MediaOnly:
			case Not:
			case And:
			case Or:
			case From:
			case To:
			case Ident:
				{
				setState(447);
				ident();
				}
				break;
			case Multiply:
				{
				setState(448);
				match(Multiply);
				}
				break;
			case Pipe:
				break;
			default:
				break;
			}
			setState(451);
			match(Pipe);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ElementNameContext extends ParserRuleContext {
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public ElementNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elementName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).enterElementName(this);
			}
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).exitElementName(this);
			}
		}
	}

	public final ElementNameContext elementName() throws RecognitionException {
		ElementNameContext _localctx = new ElementNameContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_elementName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(453);
			ident();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class UniversalContext extends ParserRuleContext {
		public TerminalNode Multiply() { return getToken(css3Parser.Multiply, 0); }
		public TypeNamespacePrefixContext typeNamespacePrefix() {
			return getRuleContext(TypeNamespacePrefixContext.class,0);
		}
		public UniversalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_universal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).enterUniversal(this);
			}
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).exitUniversal(this);
			}
		}
	}

	public final UniversalContext universal() throws RecognitionException {
		UniversalContext _localctx = new UniversalContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_universal);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(456);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,41,_ctx) ) {
			case 1:
				{
				setState(455);
				typeNamespacePrefix();
				}
				break;
			}
			setState(458);
			match(Multiply);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ClassNameContext extends ParserRuleContext {
		public TerminalNode Dot() { return getToken(css3Parser.Dot, 0); }
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public ClassNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_className; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).enterClassName(this);
			}
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).exitClassName(this);
			}
		}
	}

	public final ClassNameContext className() throws RecognitionException {
		ClassNameContext _localctx = new ClassNameContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_className);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(460);
			match(Dot);
			setState(461);
			ident();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AttribContext extends ParserRuleContext {
		public TerminalNode OpenBracket() { return getToken(css3Parser.OpenBracket, 0); }
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public List<IdentContext> ident() {
			return getRuleContexts(IdentContext.class);
		}
		public IdentContext ident(int i) {
			return getRuleContext(IdentContext.class,i);
		}
		public TerminalNode CloseBracket() { return getToken(css3Parser.CloseBracket, 0); }
		public TypeNamespacePrefixContext typeNamespacePrefix() {
			return getRuleContext(TypeNamespacePrefixContext.class,0);
		}
		public TerminalNode PrefixMatch() { return getToken(css3Parser.PrefixMatch, 0); }
		public TerminalNode SuffixMatch() { return getToken(css3Parser.SuffixMatch, 0); }
		public TerminalNode SubstringMatch() { return getToken(css3Parser.SubstringMatch, 0); }
		public TerminalNode Equal() { return getToken(css3Parser.Equal, 0); }
		public TerminalNode Includes() { return getToken(css3Parser.Includes, 0); }
		public TerminalNode DashMatch() { return getToken(css3Parser.DashMatch, 0); }
		public TerminalNode String_() { return getToken(css3Parser.String_, 0); }
		public AttribContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attrib; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).enterAttrib(this);
			}
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).exitAttrib(this);
			}
		}
	}

	public final AttribContext attrib() throws RecognitionException {
		AttribContext _localctx = new AttribContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_attrib);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(463);
			match(OpenBracket);
			setState(464);
			ws();
			setState(466);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,42,_ctx) ) {
			case 1:
				{
				setState(465);
				typeNamespacePrefix();
				}
				break;
			}
			setState(468);
			ident();
			setState(469);
			ws();
			setState(478);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 246290607767808L) != 0)) {
				{
				setState(470);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 246290607767808L) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) {
						matchedEOF = true;
					}
					_errHandler.reportMatch(this);
					consume();
				}
				setState(471);
				ws();
				setState(474);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case MediaOnly:
				case Not:
				case And:
				case Or:
				case From:
				case To:
				case Ident:
					{
					setState(472);
					ident();
					}
					break;
				case String_:
					{
					setState(473);
					match(String_);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(476);
				ws();
				}
			}

			setState(480);
			match(CloseBracket);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PseudoContext extends ParserRuleContext {
		public List<TerminalNode> Colon() { return getTokens(css3Parser.Colon); }
		public TerminalNode Colon(int i) {
			return getToken(css3Parser.Colon, i);
		}
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public FunctionalPseudoContext functionalPseudo() {
			return getRuleContext(FunctionalPseudoContext.class,0);
		}
		public PseudoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pseudo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).enterPseudo(this);
			}
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).exitPseudo(this);
			}
		}
	}

	public final PseudoContext pseudo() throws RecognitionException {
		PseudoContext _localctx = new PseudoContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_pseudo);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(482);
			match(Colon);
			setState(484);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Colon) {
				{
				setState(483);
				match(Colon);
				}
			}

			setState(488);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case MediaOnly:
			case Not:
			case And:
			case Or:
			case From:
			case To:
			case Ident:
				{
				setState(486);
				ident();
				}
				break;
			case Function_:
				{
				setState(487);
				functionalPseudo();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FunctionalPseudoContext extends ParserRuleContext {
		public TerminalNode Function_() { return getToken(css3Parser.Function_, 0); }
		public WsContext ws() {
			return getRuleContext(WsContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode CloseParen() { return getToken(css3Parser.CloseParen, 0); }
		public FunctionalPseudoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionalPseudo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).enterFunctionalPseudo(this);
			}
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).exitFunctionalPseudo(this);
			}
		}
	}

	public final FunctionalPseudoContext functionalPseudo() throws RecognitionException {
		FunctionalPseudoContext _localctx = new FunctionalPseudoContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_functionalPseudo);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(490);
			match(Function_);
			setState(491);
			ws();
			setState(492);
			expression();
			setState(493);
			match(CloseParen);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExpressionContext extends ParserRuleContext {
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public List<TerminalNode> Plus() { return getTokens(css3Parser.Plus); }
		public TerminalNode Plus(int i) {
			return getToken(css3Parser.Plus, i);
		}
		public List<TerminalNode> Minus() { return getTokens(css3Parser.Minus); }
		public TerminalNode Minus(int i) {
			return getToken(css3Parser.Minus, i);
		}
		public List<TerminalNode> Dimension() { return getTokens(css3Parser.Dimension); }
		public TerminalNode Dimension(int i) {
			return getToken(css3Parser.Dimension, i);
		}
		public List<TerminalNode> UnknownDimension() { return getTokens(css3Parser.UnknownDimension); }
		public TerminalNode UnknownDimension(int i) {
			return getToken(css3Parser.UnknownDimension, i);
		}
		public List<TerminalNode> Number() { return getTokens(css3Parser.Number); }
		public TerminalNode Number(int i) {
			return getToken(css3Parser.Number, i);
		}
		public List<TerminalNode> String_() { return getTokens(css3Parser.String_); }
		public TerminalNode String_(int i) {
			return getToken(css3Parser.String_, i);
		}
		public List<IdentContext> ident() {
			return getRuleContexts(IdentContext.class);
		}
		public IdentContext ident(int i) {
			return getRuleContext(IdentContext.class,i);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).enterExpression(this);
			}
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).exitExpression(this);
			}
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_expression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(505);
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(502);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case Plus:
					{
					setState(495);
					match(Plus);
					}
					break;
				case Minus:
					{
					setState(496);
					match(Minus);
					}
					break;
				case Dimension:
					{
					setState(497);
					match(Dimension);
					}
					break;
				case UnknownDimension:
					{
					setState(498);
					match(UnknownDimension);
					}
					break;
				case Number:
					{
					setState(499);
					match(Number);
					}
					break;
				case String_:
					{
					setState(500);
					match(String_);
					}
					break;
				case MediaOnly:
				case Not:
				case And:
				case Or:
				case From:
				case To:
				case Ident:
					{
					setState(501);
					ident();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(504);
				ws();
				}
				}
				setState(507);
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 4626349650956255232L) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class NegationContext extends ParserRuleContext {
		public TerminalNode PseudoNot() { return getToken(css3Parser.PseudoNot, 0); }
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public NegationArgContext negationArg() {
			return getRuleContext(NegationArgContext.class,0);
		}
		public TerminalNode CloseParen() { return getToken(css3Parser.CloseParen, 0); }
		public NegationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_negation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).enterNegation(this);
			}
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).exitNegation(this);
			}
		}
	}

	public final NegationContext negation() throws RecognitionException {
		NegationContext _localctx = new NegationContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_negation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(509);
			match(PseudoNot);
			setState(510);
			ws();
			setState(511);
			negationArg();
			setState(512);
			ws();
			setState(513);
			match(CloseParen);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class NegationArgContext extends ParserRuleContext {
		public TypeSelectorContext typeSelector() {
			return getRuleContext(TypeSelectorContext.class,0);
		}
		public UniversalContext universal() {
			return getRuleContext(UniversalContext.class,0);
		}
		public TerminalNode Hash() { return getToken(css3Parser.Hash, 0); }
		public ClassNameContext className() {
			return getRuleContext(ClassNameContext.class,0);
		}
		public AttribContext attrib() {
			return getRuleContext(AttribContext.class,0);
		}
		public PseudoContext pseudo() {
			return getRuleContext(PseudoContext.class,0);
		}
		public NegationArgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_negationArg; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).enterNegationArg(this);
			}
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).exitNegationArg(this);
			}
		}
	}

	public final NegationArgContext negationArg() throws RecognitionException {
		NegationArgContext _localctx = new NegationArgContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_negationArg);
		try {
			setState(521);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,49,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(515);
				typeSelector();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(516);
				universal();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(517);
				match(Hash);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(518);
				className();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(519);
				attrib();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(520);
				pseudo();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Operator_Context extends ParserRuleContext {
		public Operator_Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operator_; }

		public Operator_Context() { }
		public void copyFrom(Operator_Context ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class BadOperatorContext extends Operator_Context {
		public TerminalNode Equal() { return getToken(css3Parser.Equal, 0); }
		public WsContext ws() {
			return getRuleContext(WsContext.class,0);
		}
		public BadOperatorContext(Operator_Context ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).enterBadOperator(this);
			}
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).exitBadOperator(this);
			}
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class GoodOperatorContext extends Operator_Context {
		public TerminalNode Divide() { return getToken(css3Parser.Divide, 0); }
		public WsContext ws() {
			return getRuleContext(WsContext.class,0);
		}
		public TerminalNode Comma() { return getToken(css3Parser.Comma, 0); }
		public TerminalNode Space() { return getToken(css3Parser.Space, 0); }
		public GoodOperatorContext(Operator_Context ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).enterGoodOperator(this);
			}
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).exitGoodOperator(this);
			}
		}
	}

	public final Operator_Context operator_() throws RecognitionException {
		Operator_Context _localctx = new Operator_Context(_ctx, getState());
		enterRule(_localctx, 56, RULE_operator_);
		try {
			setState(531);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Divide:
				_localctx = new GoodOperatorContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(523);
				match(Divide);
				setState(524);
				ws();
				}
				break;
			case Comma:
				_localctx = new GoodOperatorContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(525);
				match(Comma);
				setState(526);
				ws();
				}
				break;
			case Space:
				_localctx = new GoodOperatorContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(527);
				match(Space);
				setState(528);
				ws();
				}
				break;
			case Equal:
				_localctx = new BadOperatorContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(529);
				match(Equal);
				setState(530);
				ws();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Property_Context extends ParserRuleContext {
		public Property_Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_property_; }

		public Property_Context() { }
		public void copyFrom(Property_Context ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class BadPropertyContext extends Property_Context {
		public TerminalNode Multiply() { return getToken(css3Parser.Multiply, 0); }
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public TerminalNode Underscore() { return getToken(css3Parser.Underscore, 0); }
		public BadPropertyContext(Property_Context ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).enterBadProperty(this);
			}
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).exitBadProperty(this);
			}
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class GoodPropertyContext extends Property_Context {
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public WsContext ws() {
			return getRuleContext(WsContext.class,0);
		}
		public TerminalNode Variable() { return getToken(css3Parser.Variable, 0); }
		public GoodPropertyContext(Property_Context ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).enterGoodProperty(this);
			}
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).exitGoodProperty(this);
			}
		}
	}

	public final Property_Context property_() throws RecognitionException {
		Property_Context _localctx = new Property_Context(_ctx, getState());
		enterRule(_localctx, 58, RULE_property_);
		try {
			setState(542);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case MediaOnly:
			case Not:
			case And:
			case Or:
			case From:
			case To:
			case Ident:
				_localctx = new GoodPropertyContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(533);
				ident();
				setState(534);
				ws();
				}
				break;
			case Variable:
				_localctx = new GoodPropertyContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(536);
				match(Variable);
				setState(537);
				ws();
				}
				break;
			case Multiply:
				_localctx = new BadPropertyContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(538);
				match(Multiply);
				setState(539);
				ident();
				}
				break;
			case Underscore:
				_localctx = new BadPropertyContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(540);
				match(Underscore);
				setState(541);
				ident();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RulesetContext extends ParserRuleContext {
		public RulesetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleset; }

		public RulesetContext() { }
		public void copyFrom(RulesetContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class UnknownRulesetContext extends RulesetContext {
		public TerminalNode OpenBrace() { return getToken(css3Parser.OpenBrace, 0); }
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public TerminalNode CloseBrace() { return getToken(css3Parser.CloseBrace, 0); }
		public List<Any_Context> any_() {
			return getRuleContexts(Any_Context.class);
		}
		public Any_Context any_(int i) {
			return getRuleContext(Any_Context.class,i);
		}
		public DeclarationListContext declarationList() {
			return getRuleContext(DeclarationListContext.class,0);
		}
		public UnknownRulesetContext(RulesetContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).enterUnknownRuleset(this);
			}
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).exitUnknownRuleset(this);
			}
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class KnownRulesetContext extends RulesetContext {
		public SelectorGroupContext selectorGroup() {
			return getRuleContext(SelectorGroupContext.class,0);
		}
		public TerminalNode OpenBrace() { return getToken(css3Parser.OpenBrace, 0); }
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public TerminalNode CloseBrace() { return getToken(css3Parser.CloseBrace, 0); }
		public DeclarationListContext declarationList() {
			return getRuleContext(DeclarationListContext.class,0);
		}
		public KnownRulesetContext(RulesetContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).enterKnownRuleset(this);
			}
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).exitKnownRuleset(this);
			}
		}
	}

	public final RulesetContext ruleset() throws RecognitionException {
		RulesetContext _localctx = new RulesetContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_ruleset);
		int _la;
		try {
			setState(567);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,55,_ctx) ) {
			case 1:
				_localctx = new KnownRulesetContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(544);
				selectorGroup();
				setState(545);
				match(OpenBrace);
				setState(546);
				ws();
				setState(548);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 5779244251887978624L) != 0)) {
					{
					setState(547);
					declarationList();
					}
				}

				setState(550);
				match(CloseBrace);
				setState(551);
				ws();
				}
				break;
			case 2:
				_localctx = new UnknownRulesetContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(556);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & -4597022382133018102L) != 0)) {
					{
					{
					setState(553);
					any_();
					}
					}
					setState(558);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(559);
				match(OpenBrace);
				setState(560);
				ws();
				setState(562);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 5779244251887978624L) != 0)) {
					{
					setState(561);
					declarationList();
					}
				}

				setState(564);
				match(CloseBrace);
				setState(565);
				ws();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DeclarationListContext extends ParserRuleContext {
		public List<DeclarationContext> declaration() {
			return getRuleContexts(DeclarationContext.class);
		}
		public DeclarationContext declaration(int i) {
			return getRuleContext(DeclarationContext.class,i);
		}
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public List<TerminalNode> SemiColon() { return getTokens(css3Parser.SemiColon); }
		public TerminalNode SemiColon(int i) {
			return getToken(css3Parser.SemiColon, i);
		}
		public DeclarationListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declarationList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).enterDeclarationList(this);
			}
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).exitDeclarationList(this);
			}
		}
	}

	public final DeclarationListContext declarationList() throws RecognitionException {
		DeclarationListContext _localctx = new DeclarationListContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_declarationList);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(573);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SemiColon) {
				{
				{
				setState(569);
				match(SemiColon);
				setState(570);
				ws();
				}
				}
				setState(575);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(576);
			declaration();
			setState(577);
			ws();
			setState(585);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,58,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(578);
					match(SemiColon);
					setState(579);
					ws();
					setState(581);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,57,_ctx) ) {
					case 1:
						{
						setState(580);
						declaration();
						}
						break;
					}
					}
					}
				}
				setState(587);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,58,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DeclarationContext extends ParserRuleContext {
		public DeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaration; }

		public DeclarationContext() { }
		public void copyFrom(DeclarationContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class UnknownDeclarationContext extends DeclarationContext {
		public Property_Context property_() {
			return getRuleContext(Property_Context.class,0);
		}
		public TerminalNode Colon() { return getToken(css3Parser.Colon, 0); }
		public WsContext ws() {
			return getRuleContext(WsContext.class,0);
		}
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public UnknownDeclarationContext(DeclarationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).enterUnknownDeclaration(this);
			}
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).exitUnknownDeclaration(this);
			}
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class KnownDeclarationContext extends DeclarationContext {
		public Property_Context property_() {
			return getRuleContext(Property_Context.class,0);
		}
		public TerminalNode Colon() { return getToken(css3Parser.Colon, 0); }
		public WsContext ws() {
			return getRuleContext(WsContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public PrioContext prio() {
			return getRuleContext(PrioContext.class,0);
		}
		public KnownDeclarationContext(DeclarationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).enterKnownDeclaration(this);
			}
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).exitKnownDeclaration(this);
			}
		}
	}

	public final DeclarationContext declaration() throws RecognitionException {
		DeclarationContext _localctx = new DeclarationContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_declaration);
		int _la;
		try {
			setState(600);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,60,_ctx) ) {
			case 1:
				_localctx = new KnownDeclarationContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(588);
				property_();
				setState(589);
				match(Colon);
				setState(590);
				ws();
				setState(591);
				expr();
				setState(593);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Important) {
					{
					setState(592);
					prio();
					}
				}

				}
				break;
			case 2:
				_localctx = new UnknownDeclarationContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(595);
				property_();
				setState(596);
				match(Colon);
				setState(597);
				ws();
				setState(598);
				value();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PrioContext extends ParserRuleContext {
		public TerminalNode Important() { return getToken(css3Parser.Important, 0); }
		public WsContext ws() {
			return getRuleContext(WsContext.class,0);
		}
		public PrioContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prio; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).enterPrio(this);
			}
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).exitPrio(this);
			}
		}
	}

	public final PrioContext prio() throws RecognitionException {
		PrioContext _localctx = new PrioContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_prio);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(602);
			match(Important);
			setState(603);
			ws();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ValueContext extends ParserRuleContext {
		public List<Any_Context> any_() {
			return getRuleContexts(Any_Context.class);
		}
		public Any_Context any_(int i) {
			return getRuleContext(Any_Context.class,i);
		}
		public List<BlockContext> block() {
			return getRuleContexts(BlockContext.class);
		}
		public BlockContext block(int i) {
			return getRuleContext(BlockContext.class,i);
		}
		public List<TerminalNode> AtKeyword() { return getTokens(css3Parser.AtKeyword); }
		public TerminalNode AtKeyword(int i) {
			return getToken(css3Parser.AtKeyword, i);
		}
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public ValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_value; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).enterValue(this);
			}
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).exitValue(this);
			}
		}
	}

	public final ValueContext value() throws RecognitionException {
		ValueContext _localctx = new ValueContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_value);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(609);
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					setState(609);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case OpenBracket:
					case OpenParen:
					case Colon:
					case Url:
					case Includes:
					case DashMatch:
					case Hash:
					case Percentage:
					case Url_:
					case UnicodeRange:
					case MediaOnly:
					case Not:
					case And:
					case Dimension:
					case UnknownDimension:
					case Plus:
					case Minus:
					case Number:
					case String_:
					case Or:
					case From:
					case To:
					case Ident:
					case Function_:
						{
						setState(605);
						any_();
						}
						break;
					case OpenBrace:
						{
						setState(606);
						block();
						}
						break;
					case AtKeyword:
						{
						setState(607);
						match(AtKeyword);
						setState(608);
						ws();
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(611);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,62,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExprContext extends ParserRuleContext {
		public List<TermContext> term() {
			return getRuleContexts(TermContext.class);
		}
		public TermContext term(int i) {
			return getRuleContext(TermContext.class,i);
		}
		public List<Operator_Context> operator_() {
			return getRuleContexts(Operator_Context.class);
		}
		public Operator_Context operator_(int i) {
			return getRuleContext(Operator_Context.class,i);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).enterExpr(this);
			}
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).exitExpr(this);
			}
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_expr);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(613);
			term();
			setState(620);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,64,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(615);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1099511763200L) != 0)) {
						{
						setState(614);
						operator_();
						}
					}

					setState(617);
					term();
					}
					}
				}
				setState(622);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,64,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TermContext extends ParserRuleContext {
		public TermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_term; }

		public TermContext() { }
		public void copyFrom(TermContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class BadTermContext extends TermContext {
		public DxImageTransformContext dxImageTransform() {
			return getRuleContext(DxImageTransformContext.class,0);
		}
		public BadTermContext(TermContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).enterBadTerm(this);
			}
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).exitBadTerm(this);
			}
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class KnownTermContext extends TermContext {
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public WsContext ws() {
			return getRuleContext(WsContext.class,0);
		}
		public PercentageContext percentage() {
			return getRuleContext(PercentageContext.class,0);
		}
		public DimensionContext dimension() {
			return getRuleContext(DimensionContext.class,0);
		}
		public TerminalNode String_() { return getToken(css3Parser.String_, 0); }
		public TerminalNode UnicodeRange() { return getToken(css3Parser.UnicodeRange, 0); }
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public Var_Context var_() {
			return getRuleContext(Var_Context.class,0);
		}
		public UrlContext url() {
			return getRuleContext(UrlContext.class,0);
		}
		public HexcolorContext hexcolor() {
			return getRuleContext(HexcolorContext.class,0);
		}
		public CalcContext calc() {
			return getRuleContext(CalcContext.class,0);
		}
		public Function_Context function_() {
			return getRuleContext(Function_Context.class,0);
		}
		public KnownTermContext(TermContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).enterKnownTerm(this);
			}
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).exitKnownTerm(this);
			}
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class UnknownTermContext extends TermContext {
		public UnknownDimensionContext unknownDimension() {
			return getRuleContext(UnknownDimensionContext.class,0);
		}
		public WsContext ws() {
			return getRuleContext(WsContext.class,0);
		}
		public UnknownTermContext(TermContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).enterUnknownTerm(this);
			}
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).exitUnknownTerm(this);
			}
		}
	}

	public final TermContext term() throws RecognitionException {
		TermContext _localctx = new TermContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_term);
		try {
			setState(650);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,65,_ctx) ) {
			case 1:
				_localctx = new KnownTermContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(623);
				number();
				setState(624);
				ws();
				}
				break;
			case 2:
				_localctx = new KnownTermContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(626);
				percentage();
				setState(627);
				ws();
				}
				break;
			case 3:
				_localctx = new KnownTermContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(629);
				dimension();
				setState(630);
				ws();
				}
				break;
			case 4:
				_localctx = new KnownTermContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(632);
				match(String_);
				setState(633);
				ws();
				}
				break;
			case 5:
				_localctx = new KnownTermContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(634);
				match(UnicodeRange);
				setState(635);
				ws();
				}
				break;
			case 6:
				_localctx = new KnownTermContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(636);
				ident();
				setState(637);
				ws();
				}
				break;
			case 7:
				_localctx = new KnownTermContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(639);
				var_();
				}
				break;
			case 8:
				_localctx = new KnownTermContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(640);
				url();
				setState(641);
				ws();
				}
				break;
			case 9:
				_localctx = new KnownTermContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(643);
				hexcolor();
				}
				break;
			case 10:
				_localctx = new KnownTermContext(_localctx);
				enterOuterAlt(_localctx, 10);
				{
				setState(644);
				calc();
				}
				break;
			case 11:
				_localctx = new KnownTermContext(_localctx);
				enterOuterAlt(_localctx, 11);
				{
				setState(645);
				function_();
				}
				break;
			case 12:
				_localctx = new UnknownTermContext(_localctx);
				enterOuterAlt(_localctx, 12);
				{
				setState(646);
				unknownDimension();
				setState(647);
				ws();
				}
				break;
			case 13:
				_localctx = new BadTermContext(_localctx);
				enterOuterAlt(_localctx, 13);
				{
				setState(649);
				dxImageTransform();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Function_Context extends ParserRuleContext {
		public TerminalNode Function_() { return getToken(css3Parser.Function_, 0); }
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode CloseParen() { return getToken(css3Parser.CloseParen, 0); }
		public Function_Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function_; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).enterFunction_(this);
			}
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).exitFunction_(this);
			}
		}
	}

	public final Function_Context function_() throws RecognitionException {
		Function_Context _localctx = new Function_Context(_ctx, getState());
		enterRule(_localctx, 74, RULE_function_);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(652);
			match(Function_);
			setState(653);
			ws();
			setState(654);
			expr();
			setState(655);
			match(CloseParen);
			setState(656);
			ws();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DxImageTransformContext extends ParserRuleContext {
		public TerminalNode DxImageTransform() { return getToken(css3Parser.DxImageTransform, 0); }
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode CloseParen() { return getToken(css3Parser.CloseParen, 0); }
		public DxImageTransformContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dxImageTransform; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).enterDxImageTransform(this);
			}
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).exitDxImageTransform(this);
			}
		}
	}

	public final DxImageTransformContext dxImageTransform() throws RecognitionException {
		DxImageTransformContext _localctx = new DxImageTransformContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_dxImageTransform);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(658);
			match(DxImageTransform);
			setState(659);
			ws();
			setState(660);
			expr();
			setState(661);
			match(CloseParen);
			setState(662);
			ws();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class HexcolorContext extends ParserRuleContext {
		public TerminalNode Hash() { return getToken(css3Parser.Hash, 0); }
		public WsContext ws() {
			return getRuleContext(WsContext.class,0);
		}
		public HexcolorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_hexcolor; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).enterHexcolor(this);
			}
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).exitHexcolor(this);
			}
		}
	}

	public final HexcolorContext hexcolor() throws RecognitionException {
		HexcolorContext _localctx = new HexcolorContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_hexcolor);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(664);
			match(Hash);
			setState(665);
			ws();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class NumberContext extends ParserRuleContext {
		public TerminalNode Number() { return getToken(css3Parser.Number, 0); }
		public TerminalNode Plus() { return getToken(css3Parser.Plus, 0); }
		public TerminalNode Minus() { return getToken(css3Parser.Minus, 0); }
		public NumberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_number; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).enterNumber(this);
			}
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).exitNumber(this);
			}
		}
	}

	public final NumberContext number() throws RecognitionException {
		NumberContext _localctx = new NumberContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_number);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(668);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Plus || _la==Minus) {
				{
				setState(667);
				_la = _input.LA(1);
				if ( !(_la==Plus || _la==Minus) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) {
						matchedEOF = true;
					}
					_errHandler.reportMatch(this);
					consume();
				}
				}
			}

			setState(670);
			match(Number);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PercentageContext extends ParserRuleContext {
		public TerminalNode Percentage() { return getToken(css3Parser.Percentage, 0); }
		public TerminalNode Plus() { return getToken(css3Parser.Plus, 0); }
		public TerminalNode Minus() { return getToken(css3Parser.Minus, 0); }
		public PercentageContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_percentage; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).enterPercentage(this);
			}
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).exitPercentage(this);
			}
		}
	}

	public final PercentageContext percentage() throws RecognitionException {
		PercentageContext _localctx = new PercentageContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_percentage);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(673);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Plus || _la==Minus) {
				{
				setState(672);
				_la = _input.LA(1);
				if ( !(_la==Plus || _la==Minus) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) {
						matchedEOF = true;
					}
					_errHandler.reportMatch(this);
					consume();
				}
				}
			}

			setState(675);
			match(Percentage);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DimensionContext extends ParserRuleContext {
		public TerminalNode Dimension() { return getToken(css3Parser.Dimension, 0); }
		public TerminalNode Plus() { return getToken(css3Parser.Plus, 0); }
		public TerminalNode Minus() { return getToken(css3Parser.Minus, 0); }
		public DimensionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dimension; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).enterDimension(this);
			}
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).exitDimension(this);
			}
		}
	}

	public final DimensionContext dimension() throws RecognitionException {
		DimensionContext _localctx = new DimensionContext(_ctx, getState());
		enterRule(_localctx, 84, RULE_dimension);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(678);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Plus || _la==Minus) {
				{
				setState(677);
				_la = _input.LA(1);
				if ( !(_la==Plus || _la==Minus) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) {
						matchedEOF = true;
					}
					_errHandler.reportMatch(this);
					consume();
				}
				}
			}

			setState(680);
			match(Dimension);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class UnknownDimensionContext extends ParserRuleContext {
		public TerminalNode UnknownDimension() { return getToken(css3Parser.UnknownDimension, 0); }
		public TerminalNode Plus() { return getToken(css3Parser.Plus, 0); }
		public TerminalNode Minus() { return getToken(css3Parser.Minus, 0); }
		public UnknownDimensionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unknownDimension; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).enterUnknownDimension(this);
			}
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).exitUnknownDimension(this);
			}
		}
	}

	public final UnknownDimensionContext unknownDimension() throws RecognitionException {
		UnknownDimensionContext _localctx = new UnknownDimensionContext(_ctx, getState());
		enterRule(_localctx, 86, RULE_unknownDimension);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(683);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Plus || _la==Minus) {
				{
				setState(682);
				_la = _input.LA(1);
				if ( !(_la==Plus || _la==Minus) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) {
						matchedEOF = true;
					}
					_errHandler.reportMatch(this);
					consume();
				}
				}
			}

			setState(685);
			match(UnknownDimension);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Any_Context extends ParserRuleContext {
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public PercentageContext percentage() {
			return getRuleContext(PercentageContext.class,0);
		}
		public DimensionContext dimension() {
			return getRuleContext(DimensionContext.class,0);
		}
		public UnknownDimensionContext unknownDimension() {
			return getRuleContext(UnknownDimensionContext.class,0);
		}
		public TerminalNode String_() { return getToken(css3Parser.String_, 0); }
		public UrlContext url() {
			return getRuleContext(UrlContext.class,0);
		}
		public TerminalNode Hash() { return getToken(css3Parser.Hash, 0); }
		public TerminalNode UnicodeRange() { return getToken(css3Parser.UnicodeRange, 0); }
		public TerminalNode Includes() { return getToken(css3Parser.Includes, 0); }
		public TerminalNode DashMatch() { return getToken(css3Parser.DashMatch, 0); }
		public TerminalNode Colon() { return getToken(css3Parser.Colon, 0); }
		public TerminalNode Function_() { return getToken(css3Parser.Function_, 0); }
		public TerminalNode CloseParen() { return getToken(css3Parser.CloseParen, 0); }
		public List<Any_Context> any_() {
			return getRuleContexts(Any_Context.class);
		}
		public Any_Context any_(int i) {
			return getRuleContext(Any_Context.class,i);
		}
		public List<UnusedContext> unused() {
			return getRuleContexts(UnusedContext.class);
		}
		public UnusedContext unused(int i) {
			return getRuleContext(UnusedContext.class,i);
		}
		public TerminalNode OpenParen() { return getToken(css3Parser.OpenParen, 0); }
		public TerminalNode OpenBracket() { return getToken(css3Parser.OpenBracket, 0); }
		public TerminalNode CloseBracket() { return getToken(css3Parser.CloseBracket, 0); }
		public Any_Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_any_; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).enterAny_(this);
			}
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).exitAny_(this);
			}
		}
	}

	public final Any_Context any_() throws RecognitionException {
		Any_Context _localctx = new Any_Context(_ctx, getState());
		enterRule(_localctx, 88, RULE_any_);
		int _la;
		try {
			setState(753);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,76,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(687);
				ident();
				setState(688);
				ws();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(690);
				number();
				setState(691);
				ws();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(693);
				percentage();
				setState(694);
				ws();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(696);
				dimension();
				setState(697);
				ws();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(699);
				unknownDimension();
				setState(700);
				ws();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(702);
				match(String_);
				setState(703);
				ws();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(704);
				url();
				setState(705);
				ws();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(707);
				match(Hash);
				setState(708);
				ws();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(709);
				match(UnicodeRange);
				setState(710);
				ws();
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(711);
				match(Includes);
				setState(712);
				ws();
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(713);
				match(DashMatch);
				setState(714);
				ws();
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(715);
				match(Colon);
				setState(716);
				ws();
				}
				break;
			case 13:
				enterOuterAlt(_localctx, 13);
				{
				setState(717);
				match(Function_);
				setState(718);
				ws();
				setState(723);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & -4020561629828808022L) != 0)) {
					{
					setState(721);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case OpenBracket:
					case OpenParen:
					case Colon:
					case Url:
					case Includes:
					case DashMatch:
					case Hash:
					case Percentage:
					case Url_:
					case UnicodeRange:
					case MediaOnly:
					case Not:
					case And:
					case Dimension:
					case UnknownDimension:
					case Plus:
					case Minus:
					case Number:
					case String_:
					case Or:
					case From:
					case To:
					case Ident:
					case Function_:
						{
						setState(719);
						any_();
						}
						break;
					case OpenBrace:
					case SemiColon:
					case Cdo:
					case Cdc:
					case AtKeyword:
						{
						setState(720);
						unused();
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					}
					setState(725);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(726);
				match(CloseParen);
				setState(727);
				ws();
				}
				break;
			case 14:
				enterOuterAlt(_localctx, 14);
				{
				setState(729);
				match(OpenParen);
				setState(730);
				ws();
				setState(735);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & -4020561629828808022L) != 0)) {
					{
					setState(733);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case OpenBracket:
					case OpenParen:
					case Colon:
					case Url:
					case Includes:
					case DashMatch:
					case Hash:
					case Percentage:
					case Url_:
					case UnicodeRange:
					case MediaOnly:
					case Not:
					case And:
					case Dimension:
					case UnknownDimension:
					case Plus:
					case Minus:
					case Number:
					case String_:
					case Or:
					case From:
					case To:
					case Ident:
					case Function_:
						{
						setState(731);
						any_();
						}
						break;
					case OpenBrace:
					case SemiColon:
					case Cdo:
					case Cdc:
					case AtKeyword:
						{
						setState(732);
						unused();
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					}
					setState(737);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(738);
				match(CloseParen);
				setState(739);
				ws();
				}
				break;
			case 15:
				enterOuterAlt(_localctx, 15);
				{
				setState(741);
				match(OpenBracket);
				setState(742);
				ws();
				setState(747);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & -4020561629828808022L) != 0)) {
					{
					setState(745);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case OpenBracket:
					case OpenParen:
					case Colon:
					case Url:
					case Includes:
					case DashMatch:
					case Hash:
					case Percentage:
					case Url_:
					case UnicodeRange:
					case MediaOnly:
					case Not:
					case And:
					case Dimension:
					case UnknownDimension:
					case Plus:
					case Minus:
					case Number:
					case String_:
					case Or:
					case From:
					case To:
					case Ident:
					case Function_:
						{
						setState(743);
						any_();
						}
						break;
					case OpenBrace:
					case SemiColon:
					case Cdo:
					case Cdc:
					case AtKeyword:
						{
						setState(744);
						unused();
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					}
					setState(749);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(750);
				match(CloseBracket);
				setState(751);
				ws();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AtRuleContext extends ParserRuleContext {
		public AtRuleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atRule; }

		public AtRuleContext() { }
		public void copyFrom(AtRuleContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class UnknownAtRuleContext extends AtRuleContext {
		public TerminalNode AtKeyword() { return getToken(css3Parser.AtKeyword, 0); }
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public TerminalNode SemiColon() { return getToken(css3Parser.SemiColon, 0); }
		public List<Any_Context> any_() {
			return getRuleContexts(Any_Context.class);
		}
		public Any_Context any_(int i) {
			return getRuleContext(Any_Context.class,i);
		}
		public UnknownAtRuleContext(AtRuleContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).enterUnknownAtRule(this);
			}
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).exitUnknownAtRule(this);
			}
		}
	}

	public final AtRuleContext atRule() throws RecognitionException {
		AtRuleContext _localctx = new AtRuleContext(_ctx, getState());
		enterRule(_localctx, 90, RULE_atRule);
		int _la;
		try {
			_localctx = new UnknownAtRuleContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(755);
			match(AtKeyword);
			setState(756);
			ws();
			setState(760);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & -4597022382133018102L) != 0)) {
				{
				{
				setState(757);
				any_();
				}
				}
				setState(762);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(766);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case OpenBrace:
				{
				setState(763);
				block();
				}
				break;
			case SemiColon:
				{
				setState(764);
				match(SemiColon);
				setState(765);
				ws();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class UnusedContext extends ParserRuleContext {
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public TerminalNode AtKeyword() { return getToken(css3Parser.AtKeyword, 0); }
		public WsContext ws() {
			return getRuleContext(WsContext.class,0);
		}
		public TerminalNode SemiColon() { return getToken(css3Parser.SemiColon, 0); }
		public TerminalNode Cdo() { return getToken(css3Parser.Cdo, 0); }
		public TerminalNode Cdc() { return getToken(css3Parser.Cdc, 0); }
		public UnusedContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unused; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).enterUnused(this);
			}
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).exitUnused(this);
			}
		}
	}

	public final UnusedContext unused() throws RecognitionException {
		UnusedContext _localctx = new UnusedContext(_ctx, getState());
		enterRule(_localctx, 92, RULE_unused);
		try {
			setState(777);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case OpenBrace:
				enterOuterAlt(_localctx, 1);
				{
				setState(768);
				block();
				}
				break;
			case AtKeyword:
				enterOuterAlt(_localctx, 2);
				{
				setState(769);
				match(AtKeyword);
				setState(770);
				ws();
				}
				break;
			case SemiColon:
				enterOuterAlt(_localctx, 3);
				{
				setState(771);
				match(SemiColon);
				setState(772);
				ws();
				}
				break;
			case Cdo:
				enterOuterAlt(_localctx, 4);
				{
				setState(773);
				match(Cdo);
				setState(774);
				ws();
				}
				break;
			case Cdc:
				enterOuterAlt(_localctx, 5);
				{
				setState(775);
				match(Cdc);
				setState(776);
				ws();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BlockContext extends ParserRuleContext {
		public TerminalNode OpenBrace() { return getToken(css3Parser.OpenBrace, 0); }
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public TerminalNode CloseBrace() { return getToken(css3Parser.CloseBrace, 0); }
		public List<DeclarationListContext> declarationList() {
			return getRuleContexts(DeclarationListContext.class);
		}
		public DeclarationListContext declarationList(int i) {
			return getRuleContext(DeclarationListContext.class,i);
		}
		public List<NestedStatementContext> nestedStatement() {
			return getRuleContexts(NestedStatementContext.class);
		}
		public NestedStatementContext nestedStatement(int i) {
			return getRuleContext(NestedStatementContext.class,i);
		}
		public List<Any_Context> any_() {
			return getRuleContexts(Any_Context.class);
		}
		public Any_Context any_(int i) {
			return getRuleContext(Any_Context.class,i);
		}
		public List<BlockContext> block() {
			return getRuleContexts(BlockContext.class);
		}
		public BlockContext block(int i) {
			return getRuleContext(BlockContext.class,i);
		}
		public List<TerminalNode> AtKeyword() { return getTokens(css3Parser.AtKeyword); }
		public TerminalNode AtKeyword(int i) {
			return getToken(css3Parser.AtKeyword, i);
		}
		public List<TerminalNode> SemiColon() { return getTokens(css3Parser.SemiColon); }
		public TerminalNode SemiColon(int i) {
			return getToken(css3Parser.SemiColon, i);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).enterBlock(this);
			}
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).exitBlock(this);
			}
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 94, RULE_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(779);
			match(OpenBrace);
			setState(780);
			ws();
			setState(791);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & -2612337923249312086L) != 0)) {
				{
				setState(789);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,80,_ctx) ) {
				case 1:
					{
					setState(781);
					declarationList();
					}
					break;
				case 2:
					{
					setState(782);
					nestedStatement();
					}
					break;
				case 3:
					{
					setState(783);
					any_();
					}
					break;
				case 4:
					{
					setState(784);
					block();
					}
					break;
				case 5:
					{
					setState(785);
					match(AtKeyword);
					setState(786);
					ws();
					}
					break;
				case 6:
					{
					setState(787);
					match(SemiColon);
					setState(788);
					ws();
					}
					break;
				}
				}
				setState(793);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(794);
			match(CloseBrace);
			setState(795);
			ws();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class NestedStatementContext extends ParserRuleContext {
		public RulesetContext ruleset() {
			return getRuleContext(RulesetContext.class,0);
		}
		public MediaContext media() {
			return getRuleContext(MediaContext.class,0);
		}
		public PageContext page() {
			return getRuleContext(PageContext.class,0);
		}
		public FontFaceRuleContext fontFaceRule() {
			return getRuleContext(FontFaceRuleContext.class,0);
		}
		public KeyframesRuleContext keyframesRule() {
			return getRuleContext(KeyframesRuleContext.class,0);
		}
		public SupportsRuleContext supportsRule() {
			return getRuleContext(SupportsRuleContext.class,0);
		}
		public ViewportContext viewport() {
			return getRuleContext(ViewportContext.class,0);
		}
		public CounterStyleContext counterStyle() {
			return getRuleContext(CounterStyleContext.class,0);
		}
		public FontFeatureValuesRuleContext fontFeatureValuesRule() {
			return getRuleContext(FontFeatureValuesRuleContext.class,0);
		}
		public AtRuleContext atRule() {
			return getRuleContext(AtRuleContext.class,0);
		}
		public NestedStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nestedStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).enterNestedStatement(this);
			}
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).exitNestedStatement(this);
			}
		}
	}

	public final NestedStatementContext nestedStatement() throws RecognitionException {
		NestedStatementContext _localctx = new NestedStatementContext(_ctx, getState());
		enterRule(_localctx, 96, RULE_nestedStatement);
		try {
			setState(807);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case OpenBracket:
			case OpenParen:
			case OpenBrace:
			case Colon:
			case Dot:
			case Multiply:
			case Pipe:
			case Url:
			case Includes:
			case DashMatch:
			case Hash:
			case Percentage:
			case Url_:
			case UnicodeRange:
			case MediaOnly:
			case Not:
			case And:
			case Dimension:
			case UnknownDimension:
			case Plus:
			case Minus:
			case PseudoNot:
			case Number:
			case String_:
			case Or:
			case From:
			case To:
			case Ident:
			case Function_:
				enterOuterAlt(_localctx, 1);
				{
				setState(797);
				ruleset();
				}
				break;
			case Media:
				enterOuterAlt(_localctx, 2);
				{
				setState(798);
				media();
				}
				break;
			case Page:
				enterOuterAlt(_localctx, 3);
				{
				setState(799);
				page();
				}
				break;
			case FontFace:
				enterOuterAlt(_localctx, 4);
				{
				setState(800);
				fontFaceRule();
				}
				break;
			case Keyframes:
				enterOuterAlt(_localctx, 5);
				{
				setState(801);
				keyframesRule();
				}
				break;
			case Supports:
				enterOuterAlt(_localctx, 6);
				{
				setState(802);
				supportsRule();
				}
				break;
			case Viewport:
				enterOuterAlt(_localctx, 7);
				{
				setState(803);
				viewport();
				}
				break;
			case CounterStyle:
				enterOuterAlt(_localctx, 8);
				{
				setState(804);
				counterStyle();
				}
				break;
			case FontFeatureValues:
				enterOuterAlt(_localctx, 9);
				{
				setState(805);
				fontFeatureValuesRule();
				}
				break;
			case AtKeyword:
				enterOuterAlt(_localctx, 10);
				{
				setState(806);
				atRule();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class GroupRuleBodyContext extends ParserRuleContext {
		public TerminalNode OpenBrace() { return getToken(css3Parser.OpenBrace, 0); }
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public TerminalNode CloseBrace() { return getToken(css3Parser.CloseBrace, 0); }
		public List<NestedStatementContext> nestedStatement() {
			return getRuleContexts(NestedStatementContext.class);
		}
		public NestedStatementContext nestedStatement(int i) {
			return getRuleContext(NestedStatementContext.class,i);
		}
		public GroupRuleBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_groupRuleBody; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).enterGroupRuleBody(this);
			}
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).exitGroupRuleBody(this);
			}
		}
	}

	public final GroupRuleBodyContext groupRuleBody() throws RecognitionException {
		GroupRuleBodyContext _localctx = new GroupRuleBodyContext(_ctx, getState());
		enterRule(_localctx, 98, RULE_groupRuleBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(809);
			match(OpenBrace);
			setState(810);
			ws();
			setState(814);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & -3765259427856175574L) != 0)) {
				{
				{
				setState(811);
				nestedStatement();
				}
				}
				setState(816);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(817);
			match(CloseBrace);
			setState(818);
			ws();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SupportsRuleContext extends ParserRuleContext {
		public TerminalNode Supports() { return getToken(css3Parser.Supports, 0); }
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public SupportsConditionContext supportsCondition() {
			return getRuleContext(SupportsConditionContext.class,0);
		}
		public GroupRuleBodyContext groupRuleBody() {
			return getRuleContext(GroupRuleBodyContext.class,0);
		}
		public SupportsRuleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_supportsRule; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).enterSupportsRule(this);
			}
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).exitSupportsRule(this);
			}
		}
	}

	public final SupportsRuleContext supportsRule() throws RecognitionException {
		SupportsRuleContext _localctx = new SupportsRuleContext(_ctx, getState());
		enterRule(_localctx, 100, RULE_supportsRule);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(820);
			match(Supports);
			setState(821);
			ws();
			setState(822);
			supportsCondition();
			setState(823);
			ws();
			setState(824);
			groupRuleBody();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SupportsConditionContext extends ParserRuleContext {
		public SupportsNegationContext supportsNegation() {
			return getRuleContext(SupportsNegationContext.class,0);
		}
		public SupportsConjunctionContext supportsConjunction() {
			return getRuleContext(SupportsConjunctionContext.class,0);
		}
		public SupportsDisjunctionContext supportsDisjunction() {
			return getRuleContext(SupportsDisjunctionContext.class,0);
		}
		public SupportsConditionInParensContext supportsConditionInParens() {
			return getRuleContext(SupportsConditionInParensContext.class,0);
		}
		public SupportsConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_supportsCondition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).enterSupportsCondition(this);
			}
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).exitSupportsCondition(this);
			}
		}
	}

	public final SupportsConditionContext supportsCondition() throws RecognitionException {
		SupportsConditionContext _localctx = new SupportsConditionContext(_ctx, getState());
		enterRule(_localctx, 102, RULE_supportsCondition);
		try {
			setState(830);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,84,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(826);
				supportsNegation();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(827);
				supportsConjunction();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(828);
				supportsDisjunction();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(829);
				supportsConditionInParens();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SupportsConditionInParensContext extends ParserRuleContext {
		public TerminalNode OpenParen() { return getToken(css3Parser.OpenParen, 0); }
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public SupportsConditionContext supportsCondition() {
			return getRuleContext(SupportsConditionContext.class,0);
		}
		public TerminalNode CloseParen() { return getToken(css3Parser.CloseParen, 0); }
		public SupportsDeclarationConditionContext supportsDeclarationCondition() {
			return getRuleContext(SupportsDeclarationConditionContext.class,0);
		}
		public GeneralEnclosedContext generalEnclosed() {
			return getRuleContext(GeneralEnclosedContext.class,0);
		}
		public SupportsConditionInParensContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_supportsConditionInParens; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).enterSupportsConditionInParens(this);
			}
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).exitSupportsConditionInParens(this);
			}
		}
	}

	public final SupportsConditionInParensContext supportsConditionInParens() throws RecognitionException {
		SupportsConditionInParensContext _localctx = new SupportsConditionInParensContext(_ctx, getState());
		enterRule(_localctx, 104, RULE_supportsConditionInParens);
		try {
			setState(840);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,85,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(832);
				match(OpenParen);
				setState(833);
				ws();
				setState(834);
				supportsCondition();
				setState(835);
				ws();
				setState(836);
				match(CloseParen);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(838);
				supportsDeclarationCondition();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(839);
				generalEnclosed();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SupportsNegationContext extends ParserRuleContext {
		public TerminalNode Not() { return getToken(css3Parser.Not, 0); }
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public TerminalNode Space() { return getToken(css3Parser.Space, 0); }
		public SupportsConditionInParensContext supportsConditionInParens() {
			return getRuleContext(SupportsConditionInParensContext.class,0);
		}
		public SupportsNegationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_supportsNegation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).enterSupportsNegation(this);
			}
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).exitSupportsNegation(this);
			}
		}
	}

	public final SupportsNegationContext supportsNegation() throws RecognitionException {
		SupportsNegationContext _localctx = new SupportsNegationContext(_ctx, getState());
		enterRule(_localctx, 106, RULE_supportsNegation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(842);
			match(Not);
			setState(843);
			ws();
			setState(844);
			match(Space);
			setState(845);
			ws();
			setState(846);
			supportsConditionInParens();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SupportsConjunctionContext extends ParserRuleContext {
		public List<SupportsConditionInParensContext> supportsConditionInParens() {
			return getRuleContexts(SupportsConditionInParensContext.class);
		}
		public SupportsConditionInParensContext supportsConditionInParens(int i) {
			return getRuleContext(SupportsConditionInParensContext.class,i);
		}
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public List<TerminalNode> Space() { return getTokens(css3Parser.Space); }
		public TerminalNode Space(int i) {
			return getToken(css3Parser.Space, i);
		}
		public List<TerminalNode> And() { return getTokens(css3Parser.And); }
		public TerminalNode And(int i) {
			return getToken(css3Parser.And, i);
		}
		public SupportsConjunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_supportsConjunction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).enterSupportsConjunction(this);
			}
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).exitSupportsConjunction(this);
			}
		}
	}

	public final SupportsConjunctionContext supportsConjunction() throws RecognitionException {
		SupportsConjunctionContext _localctx = new SupportsConjunctionContext(_ctx, getState());
		enterRule(_localctx, 108, RULE_supportsConjunction);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(848);
			supportsConditionInParens();
			setState(858);
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(849);
					ws();
					setState(850);
					match(Space);
					setState(851);
					ws();
					setState(852);
					match(And);
					setState(853);
					ws();
					setState(854);
					match(Space);
					setState(855);
					ws();
					setState(856);
					supportsConditionInParens();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(860);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,86,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SupportsDisjunctionContext extends ParserRuleContext {
		public List<SupportsConditionInParensContext> supportsConditionInParens() {
			return getRuleContexts(SupportsConditionInParensContext.class);
		}
		public SupportsConditionInParensContext supportsConditionInParens(int i) {
			return getRuleContext(SupportsConditionInParensContext.class,i);
		}
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public List<TerminalNode> Space() { return getTokens(css3Parser.Space); }
		public TerminalNode Space(int i) {
			return getToken(css3Parser.Space, i);
		}
		public List<TerminalNode> Or() { return getTokens(css3Parser.Or); }
		public TerminalNode Or(int i) {
			return getToken(css3Parser.Or, i);
		}
		public SupportsDisjunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_supportsDisjunction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).enterSupportsDisjunction(this);
			}
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).exitSupportsDisjunction(this);
			}
		}
	}

	public final SupportsDisjunctionContext supportsDisjunction() throws RecognitionException {
		SupportsDisjunctionContext _localctx = new SupportsDisjunctionContext(_ctx, getState());
		enterRule(_localctx, 110, RULE_supportsDisjunction);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(862);
			supportsConditionInParens();
			setState(872);
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(863);
					ws();
					setState(864);
					match(Space);
					setState(865);
					ws();
					setState(866);
					match(Or);
					setState(867);
					ws();
					setState(868);
					match(Space);
					setState(869);
					ws();
					setState(870);
					supportsConditionInParens();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(874);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,87,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SupportsDeclarationConditionContext extends ParserRuleContext {
		public TerminalNode OpenParen() { return getToken(css3Parser.OpenParen, 0); }
		public WsContext ws() {
			return getRuleContext(WsContext.class,0);
		}
		public DeclarationContext declaration() {
			return getRuleContext(DeclarationContext.class,0);
		}
		public TerminalNode CloseParen() { return getToken(css3Parser.CloseParen, 0); }
		public SupportsDeclarationConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_supportsDeclarationCondition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).enterSupportsDeclarationCondition(this);
			}
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).exitSupportsDeclarationCondition(this);
			}
		}
	}

	public final SupportsDeclarationConditionContext supportsDeclarationCondition() throws RecognitionException {
		SupportsDeclarationConditionContext _localctx = new SupportsDeclarationConditionContext(_ctx, getState());
		enterRule(_localctx, 112, RULE_supportsDeclarationCondition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(876);
			match(OpenParen);
			setState(877);
			ws();
			setState(878);
			declaration();
			setState(879);
			match(CloseParen);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class GeneralEnclosedContext extends ParserRuleContext {
		public TerminalNode CloseParen() { return getToken(css3Parser.CloseParen, 0); }
		public TerminalNode Function_() { return getToken(css3Parser.Function_, 0); }
		public TerminalNode OpenParen() { return getToken(css3Parser.OpenParen, 0); }
		public List<Any_Context> any_() {
			return getRuleContexts(Any_Context.class);
		}
		public Any_Context any_(int i) {
			return getRuleContext(Any_Context.class,i);
		}
		public List<UnusedContext> unused() {
			return getRuleContexts(UnusedContext.class);
		}
		public UnusedContext unused(int i) {
			return getRuleContext(UnusedContext.class,i);
		}
		public GeneralEnclosedContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_generalEnclosed; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).enterGeneralEnclosed(this);
			}
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).exitGeneralEnclosed(this);
			}
		}
	}

	public final GeneralEnclosedContext generalEnclosed() throws RecognitionException {
		GeneralEnclosedContext _localctx = new GeneralEnclosedContext(_ctx, getState());
		enterRule(_localctx, 114, RULE_generalEnclosed);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(881);
			_la = _input.LA(1);
			if ( !(_la==OpenParen || _la==Function_) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) {
					matchedEOF = true;
				}
				_errHandler.reportMatch(this);
				consume();
			}
			setState(886);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & -4020561629828808022L) != 0)) {
				{
				setState(884);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case OpenBracket:
				case OpenParen:
				case Colon:
				case Url:
				case Includes:
				case DashMatch:
				case Hash:
				case Percentage:
				case Url_:
				case UnicodeRange:
				case MediaOnly:
				case Not:
				case And:
				case Dimension:
				case UnknownDimension:
				case Plus:
				case Minus:
				case Number:
				case String_:
				case Or:
				case From:
				case To:
				case Ident:
				case Function_:
					{
					setState(882);
					any_();
					}
					break;
				case OpenBrace:
				case SemiColon:
				case Cdo:
				case Cdc:
				case AtKeyword:
					{
					setState(883);
					unused();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(888);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(889);
			match(CloseParen);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class UrlContext extends ParserRuleContext {
		public TerminalNode Url_() { return getToken(css3Parser.Url_, 0); }
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public TerminalNode String_() { return getToken(css3Parser.String_, 0); }
		public TerminalNode CloseParen() { return getToken(css3Parser.CloseParen, 0); }
		public TerminalNode Url() { return getToken(css3Parser.Url, 0); }
		public UrlContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_url; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).enterUrl(this);
			}
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).exitUrl(this);
			}
		}
	}

	public final UrlContext url() throws RecognitionException {
		UrlContext _localctx = new UrlContext(_ctx, getState());
		enterRule(_localctx, 116, RULE_url);
		try {
			setState(898);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Url_:
				enterOuterAlt(_localctx, 1);
				{
				setState(891);
				match(Url_);
				setState(892);
				ws();
				setState(893);
				match(String_);
				setState(894);
				ws();
				setState(895);
				match(CloseParen);
				}
				break;
			case Url:
				enterOuterAlt(_localctx, 2);
				{
				setState(897);
				match(Url);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Var_Context extends ParserRuleContext {
		public TerminalNode Var() { return getToken(css3Parser.Var, 0); }
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public TerminalNode Variable() { return getToken(css3Parser.Variable, 0); }
		public TerminalNode CloseParen() { return getToken(css3Parser.CloseParen, 0); }
		public Var_Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_var_; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).enterVar_(this);
			}
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).exitVar_(this);
			}
		}
	}

	public final Var_Context var_() throws RecognitionException {
		Var_Context _localctx = new Var_Context(_ctx, getState());
		enterRule(_localctx, 118, RULE_var_);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(900);
			match(Var);
			setState(901);
			ws();
			setState(902);
			match(Variable);
			setState(903);
			ws();
			setState(904);
			match(CloseParen);
			setState(905);
			ws();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CalcContext extends ParserRuleContext {
		public TerminalNode Calc() { return getToken(css3Parser.Calc, 0); }
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public CalcSumContext calcSum() {
			return getRuleContext(CalcSumContext.class,0);
		}
		public TerminalNode CloseParen() { return getToken(css3Parser.CloseParen, 0); }
		public CalcContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_calc; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).enterCalc(this);
			}
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).exitCalc(this);
			}
		}
	}

	public final CalcContext calc() throws RecognitionException {
		CalcContext _localctx = new CalcContext(_ctx, getState());
		enterRule(_localctx, 120, RULE_calc);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(907);
			match(Calc);
			setState(908);
			ws();
			setState(909);
			calcSum();
			setState(910);
			match(CloseParen);
			setState(911);
			ws();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CalcSumContext extends ParserRuleContext {
		public List<CalcProductContext> calcProduct() {
			return getRuleContexts(CalcProductContext.class);
		}
		public CalcProductContext calcProduct(int i) {
			return getRuleContext(CalcProductContext.class,i);
		}
		public List<TerminalNode> Space() { return getTokens(css3Parser.Space); }
		public TerminalNode Space(int i) {
			return getToken(css3Parser.Space, i);
		}
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public List<TerminalNode> Plus() { return getTokens(css3Parser.Plus); }
		public TerminalNode Plus(int i) {
			return getToken(css3Parser.Plus, i);
		}
		public List<TerminalNode> Minus() { return getTokens(css3Parser.Minus); }
		public TerminalNode Minus(int i) {
			return getToken(css3Parser.Minus, i);
		}
		public CalcSumContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_calcSum; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).enterCalcSum(this);
			}
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).exitCalcSum(this);
			}
		}
	}

	public final CalcSumContext calcSum() throws RecognitionException {
		CalcSumContext _localctx = new CalcSumContext(_ctx, getState());
		enterRule(_localctx, 122, RULE_calcSum);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(913);
			calcProduct();
			setState(924);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Space) {
				{
				{
				setState(914);
				match(Space);
				setState(915);
				ws();
				setState(916);
				_la = _input.LA(1);
				if ( !(_la==Plus || _la==Minus) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) {
						matchedEOF = true;
					}
					_errHandler.reportMatch(this);
					consume();
				}
				setState(917);
				ws();
				setState(918);
				match(Space);
				setState(919);
				ws();
				setState(920);
				calcProduct();
				}
				}
				setState(926);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CalcProductContext extends ParserRuleContext {
		public List<CalcValueContext> calcValue() {
			return getRuleContexts(CalcValueContext.class);
		}
		public CalcValueContext calcValue(int i) {
			return getRuleContext(CalcValueContext.class,i);
		}
		public List<TerminalNode> Multiply() { return getTokens(css3Parser.Multiply); }
		public TerminalNode Multiply(int i) {
			return getToken(css3Parser.Multiply, i);
		}
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public List<TerminalNode> Divide() { return getTokens(css3Parser.Divide); }
		public TerminalNode Divide(int i) {
			return getToken(css3Parser.Divide, i);
		}
		public List<NumberContext> number() {
			return getRuleContexts(NumberContext.class);
		}
		public NumberContext number(int i) {
			return getRuleContext(NumberContext.class,i);
		}
		public CalcProductContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_calcProduct; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).enterCalcProduct(this);
			}
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).exitCalcProduct(this);
			}
		}
	}

	public final CalcProductContext calcProduct() throws RecognitionException {
		CalcProductContext _localctx = new CalcProductContext(_ctx, getState());
		enterRule(_localctx, 124, RULE_calcProduct);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(927);
			calcValue();
			setState(939);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Multiply || _la==Divide) {
				{
				setState(937);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case Multiply:
					{
					setState(928);
					match(Multiply);
					setState(929);
					ws();
					setState(930);
					calcValue();
					}
					break;
				case Divide:
					{
					setState(932);
					match(Divide);
					setState(933);
					ws();
					setState(934);
					number();
					setState(935);
					ws();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(941);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CalcValueContext extends ParserRuleContext {
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public DimensionContext dimension() {
			return getRuleContext(DimensionContext.class,0);
		}
		public UnknownDimensionContext unknownDimension() {
			return getRuleContext(UnknownDimensionContext.class,0);
		}
		public PercentageContext percentage() {
			return getRuleContext(PercentageContext.class,0);
		}
		public TerminalNode OpenParen() { return getToken(css3Parser.OpenParen, 0); }
		public CalcSumContext calcSum() {
			return getRuleContext(CalcSumContext.class,0);
		}
		public TerminalNode CloseParen() { return getToken(css3Parser.CloseParen, 0); }
		public CalcValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_calcValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).enterCalcValue(this);
			}
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).exitCalcValue(this);
			}
		}
	}

	public final CalcValueContext calcValue() throws RecognitionException {
		CalcValueContext _localctx = new CalcValueContext(_ctx, getState());
		enterRule(_localctx, 126, RULE_calcValue);
		try {
			setState(960);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,94,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(942);
				number();
				setState(943);
				ws();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(945);
				dimension();
				setState(946);
				ws();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(948);
				unknownDimension();
				setState(949);
				ws();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(951);
				percentage();
				setState(952);
				ws();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(954);
				match(OpenParen);
				setState(955);
				ws();
				setState(956);
				calcSum();
				setState(957);
				match(CloseParen);
				setState(958);
				ws();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FontFaceRuleContext extends ParserRuleContext {
		public TerminalNode FontFace() { return getToken(css3Parser.FontFace, 0); }
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public TerminalNode OpenBrace() { return getToken(css3Parser.OpenBrace, 0); }
		public TerminalNode CloseBrace() { return getToken(css3Parser.CloseBrace, 0); }
		public List<FontFaceDeclarationContext> fontFaceDeclaration() {
			return getRuleContexts(FontFaceDeclarationContext.class);
		}
		public FontFaceDeclarationContext fontFaceDeclaration(int i) {
			return getRuleContext(FontFaceDeclarationContext.class,i);
		}
		public List<TerminalNode> SemiColon() { return getTokens(css3Parser.SemiColon); }
		public TerminalNode SemiColon(int i) {
			return getToken(css3Parser.SemiColon, i);
		}
		public FontFaceRuleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fontFaceRule; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).enterFontFaceRule(this);
			}
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).exitFontFaceRule(this);
			}
		}
	}

	public final FontFaceRuleContext fontFaceRule() throws RecognitionException {
		FontFaceRuleContext _localctx = new FontFaceRuleContext(_ctx, getState());
		enterRule(_localctx, 128, RULE_fontFaceRule);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(962);
			match(FontFace);
			setState(963);
			ws();
			setState(964);
			match(OpenBrace);
			setState(965);
			ws();
			setState(967);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 5779244251887978496L) != 0)) {
				{
				setState(966);
				fontFaceDeclaration();
				}
			}

			setState(976);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SemiColon) {
				{
				{
				setState(969);
				match(SemiColon);
				setState(970);
				ws();
				setState(972);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 5779244251887978496L) != 0)) {
					{
					setState(971);
					fontFaceDeclaration();
					}
				}

				}
				}
				setState(978);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(979);
			match(CloseBrace);
			setState(980);
			ws();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FontFaceDeclarationContext extends ParserRuleContext {
		public FontFaceDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fontFaceDeclaration; }

		public FontFaceDeclarationContext() { }
		public void copyFrom(FontFaceDeclarationContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class KnownFontFaceDeclarationContext extends FontFaceDeclarationContext {
		public Property_Context property_() {
			return getRuleContext(Property_Context.class,0);
		}
		public TerminalNode Colon() { return getToken(css3Parser.Colon, 0); }
		public WsContext ws() {
			return getRuleContext(WsContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public KnownFontFaceDeclarationContext(FontFaceDeclarationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).enterKnownFontFaceDeclaration(this);
			}
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).exitKnownFontFaceDeclaration(this);
			}
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class UnknownFontFaceDeclarationContext extends FontFaceDeclarationContext {
		public Property_Context property_() {
			return getRuleContext(Property_Context.class,0);
		}
		public TerminalNode Colon() { return getToken(css3Parser.Colon, 0); }
		public WsContext ws() {
			return getRuleContext(WsContext.class,0);
		}
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public UnknownFontFaceDeclarationContext(FontFaceDeclarationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).enterUnknownFontFaceDeclaration(this);
			}
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).exitUnknownFontFaceDeclaration(this);
			}
		}
	}

	public final FontFaceDeclarationContext fontFaceDeclaration() throws RecognitionException {
		FontFaceDeclarationContext _localctx = new FontFaceDeclarationContext(_ctx, getState());
		enterRule(_localctx, 130, RULE_fontFaceDeclaration);
		try {
			setState(992);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,98,_ctx) ) {
			case 1:
				_localctx = new KnownFontFaceDeclarationContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(982);
				property_();
				setState(983);
				match(Colon);
				setState(984);
				ws();
				setState(985);
				expr();
				}
				break;
			case 2:
				_localctx = new UnknownFontFaceDeclarationContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(987);
				property_();
				setState(988);
				match(Colon);
				setState(989);
				ws();
				setState(990);
				value();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class KeyframesRuleContext extends ParserRuleContext {
		public TerminalNode Keyframes() { return getToken(css3Parser.Keyframes, 0); }
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public TerminalNode Space() { return getToken(css3Parser.Space, 0); }
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public TerminalNode OpenBrace() { return getToken(css3Parser.OpenBrace, 0); }
		public TerminalNode CloseBrace() { return getToken(css3Parser.CloseBrace, 0); }
		public List<KeyframeBlockContext> keyframeBlock() {
			return getRuleContexts(KeyframeBlockContext.class);
		}
		public KeyframeBlockContext keyframeBlock(int i) {
			return getRuleContext(KeyframeBlockContext.class,i);
		}
		public KeyframesRuleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_keyframesRule; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).enterKeyframesRule(this);
			}
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).exitKeyframesRule(this);
			}
		}
	}

	public final KeyframesRuleContext keyframesRule() throws RecognitionException {
		KeyframesRuleContext _localctx = new KeyframesRuleContext(_ctx, getState());
		enterRule(_localctx, 132, RULE_keyframesRule);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(994);
			match(Keyframes);
			setState(995);
			ws();
			setState(996);
			match(Space);
			setState(997);
			ws();
			setState(998);
			ident();
			setState(999);
			ws();
			setState(1000);
			match(OpenBrace);
			setState(1001);
			ws();
			setState(1005);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 13510799418982400L) != 0)) {
				{
				{
				setState(1002);
				keyframeBlock();
				}
				}
				setState(1007);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1008);
			match(CloseBrace);
			setState(1009);
			ws();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class KeyframeBlockContext extends ParserRuleContext {
		public KeyframeSelectorContext keyframeSelector() {
			return getRuleContext(KeyframeSelectorContext.class,0);
		}
		public TerminalNode OpenBrace() { return getToken(css3Parser.OpenBrace, 0); }
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public TerminalNode CloseBrace() { return getToken(css3Parser.CloseBrace, 0); }
		public DeclarationListContext declarationList() {
			return getRuleContext(DeclarationListContext.class,0);
		}
		public KeyframeBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_keyframeBlock; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).enterKeyframeBlock(this);
			}
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).exitKeyframeBlock(this);
			}
		}
	}

	public final KeyframeBlockContext keyframeBlock() throws RecognitionException {
		KeyframeBlockContext _localctx = new KeyframeBlockContext(_ctx, getState());
		enterRule(_localctx, 134, RULE_keyframeBlock);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(1011);
			keyframeSelector();
			setState(1012);
			match(OpenBrace);
			setState(1013);
			ws();
			setState(1015);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 5779244251887978624L) != 0)) {
				{
				setState(1014);
				declarationList();
				}
			}

			setState(1017);
			match(CloseBrace);
			setState(1018);
			ws();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class KeyframeSelectorContext extends ParserRuleContext {
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public List<TerminalNode> From() { return getTokens(css3Parser.From); }
		public TerminalNode From(int i) {
			return getToken(css3Parser.From, i);
		}
		public List<TerminalNode> To() { return getTokens(css3Parser.To); }
		public TerminalNode To(int i) {
			return getToken(css3Parser.To, i);
		}
		public List<TerminalNode> Percentage() { return getTokens(css3Parser.Percentage); }
		public TerminalNode Percentage(int i) {
			return getToken(css3Parser.Percentage, i);
		}
		public List<TerminalNode> Comma() { return getTokens(css3Parser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(css3Parser.Comma, i);
		}
		public KeyframeSelectorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_keyframeSelector; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).enterKeyframeSelector(this);
			}
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).exitKeyframeSelector(this);
			}
		}
	}

	public final KeyframeSelectorContext keyframeSelector() throws RecognitionException {
		KeyframeSelectorContext _localctx = new KeyframeSelectorContext(_ctx, getState());
		enterRule(_localctx, 136, RULE_keyframeSelector);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1020);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 13510799418982400L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) {
					matchedEOF = true;
				}
				_errHandler.reportMatch(this);
				consume();
			}
			setState(1021);
			ws();
			setState(1029);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Comma) {
				{
				{
				setState(1022);
				match(Comma);
				setState(1023);
				ws();
				setState(1024);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 13510799418982400L) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) {
						matchedEOF = true;
					}
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1025);
				ws();
				}
				}
				setState(1031);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ViewportContext extends ParserRuleContext {
		public TerminalNode Viewport() { return getToken(css3Parser.Viewport, 0); }
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public TerminalNode OpenBrace() { return getToken(css3Parser.OpenBrace, 0); }
		public TerminalNode CloseBrace() { return getToken(css3Parser.CloseBrace, 0); }
		public DeclarationListContext declarationList() {
			return getRuleContext(DeclarationListContext.class,0);
		}
		public ViewportContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_viewport; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).enterViewport(this);
			}
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).exitViewport(this);
			}
		}
	}

	public final ViewportContext viewport() throws RecognitionException {
		ViewportContext _localctx = new ViewportContext(_ctx, getState());
		enterRule(_localctx, 138, RULE_viewport);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1032);
			match(Viewport);
			setState(1033);
			ws();
			setState(1034);
			match(OpenBrace);
			setState(1035);
			ws();
			setState(1037);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 5779244251887978624L) != 0)) {
				{
				setState(1036);
				declarationList();
				}
			}

			setState(1039);
			match(CloseBrace);
			setState(1040);
			ws();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CounterStyleContext extends ParserRuleContext {
		public TerminalNode CounterStyle() { return getToken(css3Parser.CounterStyle, 0); }
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public TerminalNode OpenBrace() { return getToken(css3Parser.OpenBrace, 0); }
		public TerminalNode CloseBrace() { return getToken(css3Parser.CloseBrace, 0); }
		public DeclarationListContext declarationList() {
			return getRuleContext(DeclarationListContext.class,0);
		}
		public CounterStyleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_counterStyle; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).enterCounterStyle(this);
			}
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).exitCounterStyle(this);
			}
		}
	}

	public final CounterStyleContext counterStyle() throws RecognitionException {
		CounterStyleContext _localctx = new CounterStyleContext(_ctx, getState());
		enterRule(_localctx, 140, RULE_counterStyle);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1042);
			match(CounterStyle);
			setState(1043);
			ws();
			setState(1044);
			ident();
			setState(1045);
			ws();
			setState(1046);
			match(OpenBrace);
			setState(1047);
			ws();
			setState(1049);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 5779244251887978624L) != 0)) {
				{
				setState(1048);
				declarationList();
				}
			}

			setState(1051);
			match(CloseBrace);
			setState(1052);
			ws();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FontFeatureValuesRuleContext extends ParserRuleContext {
		public TerminalNode FontFeatureValues() { return getToken(css3Parser.FontFeatureValues, 0); }
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public FontFamilyNameListContext fontFamilyNameList() {
			return getRuleContext(FontFamilyNameListContext.class,0);
		}
		public TerminalNode OpenBrace() { return getToken(css3Parser.OpenBrace, 0); }
		public TerminalNode CloseBrace() { return getToken(css3Parser.CloseBrace, 0); }
		public List<FeatureValueBlockContext> featureValueBlock() {
			return getRuleContexts(FeatureValueBlockContext.class);
		}
		public FeatureValueBlockContext featureValueBlock(int i) {
			return getRuleContext(FeatureValueBlockContext.class,i);
		}
		public FontFeatureValuesRuleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fontFeatureValuesRule; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).enterFontFeatureValuesRule(this);
			}
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).exitFontFeatureValuesRule(this);
			}
		}
	}

	public final FontFeatureValuesRuleContext fontFeatureValuesRule() throws RecognitionException {
		FontFeatureValuesRuleContext _localctx = new FontFeatureValuesRuleContext(_ctx, getState());
		enterRule(_localctx, 142, RULE_fontFeatureValuesRule);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1054);
			match(FontFeatureValues);
			setState(1055);
			ws();
			setState(1056);
			fontFamilyNameList();
			setState(1057);
			ws();
			setState(1058);
			match(OpenBrace);
			setState(1059);
			ws();
			setState(1063);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AtKeyword) {
				{
				{
				setState(1060);
				featureValueBlock();
				}
				}
				setState(1065);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1066);
			match(CloseBrace);
			setState(1067);
			ws();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FontFamilyNameListContext extends ParserRuleContext {
		public List<FontFamilyNameContext> fontFamilyName() {
			return getRuleContexts(FontFamilyNameContext.class);
		}
		public FontFamilyNameContext fontFamilyName(int i) {
			return getRuleContext(FontFamilyNameContext.class,i);
		}
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public List<TerminalNode> Comma() { return getTokens(css3Parser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(css3Parser.Comma, i);
		}
		public FontFamilyNameListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fontFamilyNameList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).enterFontFamilyNameList(this);
			}
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).exitFontFamilyNameList(this);
			}
		}
	}

	public final FontFamilyNameListContext fontFamilyNameList() throws RecognitionException {
		FontFamilyNameListContext _localctx = new FontFamilyNameListContext(_ctx, getState());
		enterRule(_localctx, 144, RULE_fontFamilyNameList);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1069);
			fontFamilyName();
			setState(1077);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,105,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1070);
					ws();
					setState(1071);
					match(Comma);
					setState(1072);
					ws();
					setState(1073);
					fontFamilyName();
					}
					}
				}
				setState(1079);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,105,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FontFamilyNameContext extends ParserRuleContext {
		public TerminalNode String_() { return getToken(css3Parser.String_, 0); }
		public List<IdentContext> ident() {
			return getRuleContexts(IdentContext.class);
		}
		public IdentContext ident(int i) {
			return getRuleContext(IdentContext.class,i);
		}
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public FontFamilyNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fontFamilyName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).enterFontFamilyName(this);
			}
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).exitFontFamilyName(this);
			}
		}
	}

	public final FontFamilyNameContext fontFamilyName() throws RecognitionException {
		FontFamilyNameContext _localctx = new FontFamilyNameContext(_ctx, getState());
		enterRule(_localctx, 146, RULE_fontFamilyName);
		try {
			int _alt;
			setState(1090);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case String_:
				enterOuterAlt(_localctx, 1);
				{
				setState(1080);
				match(String_);
				}
				break;
			case MediaOnly:
			case Not:
			case And:
			case Or:
			case From:
			case To:
			case Ident:
				enterOuterAlt(_localctx, 2);
				{
				setState(1081);
				ident();
				setState(1087);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,106,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(1082);
						ws();
						setState(1083);
						ident();
						}
						}
					}
					setState(1089);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,106,_ctx);
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FeatureValueBlockContext extends ParserRuleContext {
		public FeatureTypeContext featureType() {
			return getRuleContext(FeatureTypeContext.class,0);
		}
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public TerminalNode OpenBrace() { return getToken(css3Parser.OpenBrace, 0); }
		public TerminalNode CloseBrace() { return getToken(css3Parser.CloseBrace, 0); }
		public List<FeatureValueDefinitionContext> featureValueDefinition() {
			return getRuleContexts(FeatureValueDefinitionContext.class);
		}
		public FeatureValueDefinitionContext featureValueDefinition(int i) {
			return getRuleContext(FeatureValueDefinitionContext.class,i);
		}
		public List<TerminalNode> SemiColon() { return getTokens(css3Parser.SemiColon); }
		public TerminalNode SemiColon(int i) {
			return getToken(css3Parser.SemiColon, i);
		}
		public FeatureValueBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_featureValueBlock; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).enterFeatureValueBlock(this);
			}
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).exitFeatureValueBlock(this);
			}
		}
	}

	public final FeatureValueBlockContext featureValueBlock() throws RecognitionException {
		FeatureValueBlockContext _localctx = new FeatureValueBlockContext(_ctx, getState());
		enterRule(_localctx, 148, RULE_featureValueBlock);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1092);
			featureType();
			setState(1093);
			ws();
			setState(1094);
			match(OpenBrace);
			setState(1095);
			ws();
			setState(1097);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 4626322747281113088L) != 0)) {
				{
				setState(1096);
				featureValueDefinition();
				}
			}

			setState(1107);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 163968L) != 0)) {
				{
				{
				setState(1099);
				ws();
				setState(1100);
				match(SemiColon);
				setState(1101);
				ws();
				setState(1103);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 4626322747281113088L) != 0)) {
					{
					setState(1102);
					featureValueDefinition();
					}
				}

				}
				}
				setState(1109);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1110);
			match(CloseBrace);
			setState(1111);
			ws();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FeatureTypeContext extends ParserRuleContext {
		public TerminalNode AtKeyword() { return getToken(css3Parser.AtKeyword, 0); }
		public FeatureTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_featureType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).enterFeatureType(this);
			}
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).exitFeatureType(this);
			}
		}
	}

	public final FeatureTypeContext featureType() throws RecognitionException {
		FeatureTypeContext _localctx = new FeatureTypeContext(_ctx, getState());
		enterRule(_localctx, 150, RULE_featureType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1113);
			match(AtKeyword);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FeatureValueDefinitionContext extends ParserRuleContext {
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public TerminalNode Colon() { return getToken(css3Parser.Colon, 0); }
		public List<NumberContext> number() {
			return getRuleContexts(NumberContext.class);
		}
		public NumberContext number(int i) {
			return getRuleContext(NumberContext.class,i);
		}
		public FeatureValueDefinitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_featureValueDefinition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).enterFeatureValueDefinition(this);
			}
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).exitFeatureValueDefinition(this);
			}
		}
	}

	public final FeatureValueDefinitionContext featureValueDefinition() throws RecognitionException {
		FeatureValueDefinitionContext _localctx = new FeatureValueDefinitionContext(_ctx, getState());
		enterRule(_localctx, 152, RULE_featureValueDefinition);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1115);
			ident();
			setState(1116);
			ws();
			setState(1117);
			match(Colon);
			setState(1118);
			ws();
			setState(1119);
			number();
			setState(1125);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,111,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1120);
					ws();
					setState(1121);
					number();
					}
					}
				}
				setState(1127);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,111,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class IdentContext extends ParserRuleContext {
		public TerminalNode Ident() { return getToken(css3Parser.Ident, 0); }
		public TerminalNode MediaOnly() { return getToken(css3Parser.MediaOnly, 0); }
		public TerminalNode Not() { return getToken(css3Parser.Not, 0); }
		public TerminalNode And() { return getToken(css3Parser.And, 0); }
		public TerminalNode Or() { return getToken(css3Parser.Or, 0); }
		public TerminalNode From() { return getToken(css3Parser.From, 0); }
		public TerminalNode To() { return getToken(css3Parser.To, 0); }
		public IdentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ident; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).enterIdent(this);
			}
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).exitIdent(this);
			}
		}
	}

	public final IdentContext ident() throws RecognitionException {
		IdentContext _localctx = new IdentContext(_ctx, getState());
		enterRule(_localctx, 154, RULE_ident);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1128);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 4626322747281113088L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) {
					matchedEOF = true;
				}
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class WsContext extends ParserRuleContext {
		public List<TerminalNode> Comment() { return getTokens(css3Parser.Comment); }
		public TerminalNode Comment(int i) {
			return getToken(css3Parser.Comment, i);
		}
		public List<TerminalNode> Space() { return getTokens(css3Parser.Space); }
		public TerminalNode Space(int i) {
			return getToken(css3Parser.Space, i);
		}
		public WsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ws; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).enterWs(this);
			}
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof css3ParserListener ) {
				((css3ParserListener)listener).exitWs(this);
			}
		}
	}

	public final WsContext ws() throws RecognitionException {
		WsContext _localctx = new WsContext(_ctx, getState());
		enterRule(_localctx, 156, RULE_ws);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1133);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,112,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1130);
					_la = _input.LA(1);
					if ( !(_la==Comment || _la==Space) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) {
							matchedEOF = true;
						}
						_errHandler.reportMatch(this);
						consume();
					}
					}
					}
				}
				setState(1135);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,112,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\u0004\u0001@\u0471\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018"+
		"\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002\u001b\u0007\u001b"+
		"\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002\u001e\u0007\u001e"+
		"\u0002\u001f\u0007\u001f\u0002 \u0007 \u0002!\u0007!\u0002\"\u0007\"\u0002"+
		"#\u0007#\u0002$\u0007$\u0002%\u0007%\u0002&\u0007&\u0002\'\u0007\'\u0002"+
		"(\u0007(\u0002)\u0007)\u0002*\u0007*\u0002+\u0007+\u0002,\u0007,\u0002"+
		"-\u0007-\u0002.\u0007.\u0002/\u0007/\u00020\u00070\u00021\u00071\u0002"+
		"2\u00072\u00023\u00073\u00024\u00074\u00025\u00075\u00026\u00076\u0002"+
		"7\u00077\u00028\u00078\u00029\u00079\u0002:\u0007:\u0002;\u0007;\u0002"+
		"<\u0007<\u0002=\u0007=\u0002>\u0007>\u0002?\u0007?\u0002@\u0007@\u0002"+
		"A\u0007A\u0002B\u0007B\u0002C\u0007C\u0002D\u0007D\u0002E\u0007E\u0002"+
		"F\u0007F\u0002G\u0007G\u0002H\u0007H\u0002I\u0007I\u0002J\u0007J\u0002"+
		"K\u0007K\u0002L\u0007L\u0002M\u0007M\u0002N\u0007N\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0005\u0000\u00a2\b\u0000\n\u0000\f\u0000\u00a5\t\u0000\u0005"+
		"\u0000\u00a7\b\u0000\n\u0000\f\u0000\u00aa\t\u0000\u0001\u0000\u0001\u0000"+
		"\u0005\u0000\u00ae\b\u0000\n\u0000\f\u0000\u00b1\t\u0000\u0005\u0000\u00b3"+
		"\b\u0000\n\u0000\f\u0000\u00b6\t\u0000\u0001\u0000\u0001\u0000\u0005\u0000"+
		"\u00ba\b\u0000\n\u0000\f\u0000\u00bd\t\u0000\u0005\u0000\u00bf\b\u0000"+
		"\n\u0000\f\u0000\u00c2\t\u0000\u0001\u0000\u0001\u0000\u0005\u0000\u00c6"+
		"\b\u0000\n\u0000\f\u0000\u00c9\t\u0000\u0005\u0000\u00cb\b\u0000\n\u0000"+
		"\f\u0000\u00ce\t\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0003\u0001\u00de\b\u0001"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0003\u0002\u00e4\b\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0003\u0002\u00ef\b\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0003\u0002\u00f9\b\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0003\u0002\u0102\b\u0002"+
		"\u0001\u0002\u0001\u0002\u0003\u0002\u0106\b\u0002\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0003\u0003\u010d\b\u0003\u0001\u0003"+
		"\u0001\u0003\u0003\u0003\u0111\b\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0003\u0003\u011c\b\u0003\u0001\u0003\u0001\u0003\u0003\u0003\u0120\b"+
		"\u0003\u0001\u0003\u0001\u0003\u0003\u0003\u0124\b\u0003\u0001\u0004\u0001"+
		"\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0005"+
		"\u0006\u0133\b\u0006\n\u0006\f\u0006\u0136\t\u0006\u0003\u0006\u0138\b"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0007\u0003\u0007\u013d\b\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0005\u0007\u0146\b\u0007\n\u0007\f\u0007\u0149\t\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0005\u0007\u0150\b\u0007"+
		"\n\u0007\f\u0007\u0153\t\u0007\u0003\u0007\u0155\b\u0007\u0001\b\u0001"+
		"\b\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0003\t\u0160"+
		"\b\t\u0001\t\u0001\t\u0001\t\u0001\n\u0001\n\u0001\n\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0003\u000b\u016b\b\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0003\u000b\u0170\b\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0003"+
		"\u000b\u0175\b\u000b\u0005\u000b\u0177\b\u000b\n\u000b\f\u000b\u017a\t"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0005\r\u0188\b\r\n\r\f\r\u018b"+
		"\t\r\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001"+
		"\u000e\u0005\u000e\u0193\b\u000e\n\u000e\f\u000e\u0196\t\u000e\u0001\u000f"+
		"\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f"+
		"\u0001\u000f\u0003\u000f\u01a0\b\u000f\u0001\u0010\u0001\u0010\u0003\u0010"+
		"\u01a4\b\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010"+
		"\u0005\u0010\u01ab\b\u0010\n\u0010\f\u0010\u01ae\t\u0010\u0001\u0010\u0001"+
		"\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0004\u0010\u01b5\b\u0010\u000b"+
		"\u0010\f\u0010\u01b6\u0003\u0010\u01b9\b\u0010\u0001\u0011\u0003\u0011"+
		"\u01bc\b\u0011\u0001\u0011\u0001\u0011\u0001\u0012\u0001\u0012\u0003\u0012"+
		"\u01c2\b\u0012\u0001\u0012\u0001\u0012\u0001\u0013\u0001\u0013\u0001\u0014"+
		"\u0003\u0014\u01c9\b\u0014\u0001\u0014\u0001\u0014\u0001\u0015\u0001\u0015"+
		"\u0001\u0015\u0001\u0016\u0001\u0016\u0001\u0016\u0003\u0016\u01d3\b\u0016"+
		"\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016"+
		"\u0003\u0016\u01db\b\u0016\u0001\u0016\u0001\u0016\u0003\u0016\u01df\b"+
		"\u0016\u0001\u0016\u0001\u0016\u0001\u0017\u0001\u0017\u0003\u0017\u01e5"+
		"\b\u0017\u0001\u0017\u0001\u0017\u0003\u0017\u01e9\b\u0017\u0001\u0018"+
		"\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0019\u0001\u0019"+
		"\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0003\u0019"+
		"\u01f7\b\u0019\u0001\u0019\u0004\u0019\u01fa\b\u0019\u000b\u0019\f\u0019"+
		"\u01fb\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0001"+
		"\u001a\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001"+
		"\u001b\u0003\u001b\u020a\b\u001b\u0001\u001c\u0001\u001c\u0001\u001c\u0001"+
		"\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0003\u001c\u0214"+
		"\b\u001c\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001d\u0001"+
		"\u001d\u0001\u001d\u0001\u001d\u0001\u001d\u0003\u001d\u021f\b\u001d\u0001"+
		"\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0003\u001e\u0225\b\u001e\u0001"+
		"\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0005\u001e\u022b\b\u001e\n"+
		"\u001e\f\u001e\u022e\t\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0003"+
		"\u001e\u0233\b\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0003\u001e\u0238"+
		"\b\u001e\u0001\u001f\u0001\u001f\u0005\u001f\u023c\b\u001f\n\u001f\f\u001f"+
		"\u023f\t\u001f\u0001\u001f\u0001\u001f\u0001\u001f\u0001\u001f\u0001\u001f"+
		"\u0003\u001f\u0246\b\u001f\u0005\u001f\u0248\b\u001f\n\u001f\f\u001f\u024b"+
		"\t\u001f\u0001 \u0001 \u0001 \u0001 \u0001 \u0003 \u0252\b \u0001 \u0001"+
		" \u0001 \u0001 \u0001 \u0003 \u0259\b \u0001!\u0001!\u0001!\u0001\"\u0001"+
		"\"\u0001\"\u0001\"\u0004\"\u0262\b\"\u000b\"\f\"\u0263\u0001#\u0001#\u0003"+
		"#\u0268\b#\u0001#\u0005#\u026b\b#\n#\f#\u026e\t#\u0001$\u0001$\u0001$"+
		"\u0001$\u0001$\u0001$\u0001$\u0001$\u0001$\u0001$\u0001$\u0001$\u0001"+
		"$\u0001$\u0001$\u0001$\u0001$\u0001$\u0001$\u0001$\u0001$\u0001$\u0001"+
		"$\u0001$\u0001$\u0001$\u0001$\u0003$\u028b\b$\u0001%\u0001%\u0001%\u0001"+
		"%\u0001%\u0001%\u0001&\u0001&\u0001&\u0001&\u0001&\u0001&\u0001\'\u0001"+
		"\'\u0001\'\u0001(\u0003(\u029d\b(\u0001(\u0001(\u0001)\u0003)\u02a2\b"+
		")\u0001)\u0001)\u0001*\u0003*\u02a7\b*\u0001*\u0001*\u0001+\u0003+\u02ac"+
		"\b+\u0001+\u0001+\u0001,\u0001,\u0001,\u0001,\u0001,\u0001,\u0001,\u0001"+
		",\u0001,\u0001,\u0001,\u0001,\u0001,\u0001,\u0001,\u0001,\u0001,\u0001"+
		",\u0001,\u0001,\u0001,\u0001,\u0001,\u0001,\u0001,\u0001,\u0001,\u0001"+
		",\u0001,\u0001,\u0001,\u0001,\u0001,\u0001,\u0005,\u02d2\b,\n,\f,\u02d5"+
		"\t,\u0001,\u0001,\u0001,\u0001,\u0001,\u0001,\u0001,\u0005,\u02de\b,\n"+
		",\f,\u02e1\t,\u0001,\u0001,\u0001,\u0001,\u0001,\u0001,\u0001,\u0005,"+
		"\u02ea\b,\n,\f,\u02ed\t,\u0001,\u0001,\u0001,\u0003,\u02f2\b,\u0001-\u0001"+
		"-\u0001-\u0005-\u02f7\b-\n-\f-\u02fa\t-\u0001-\u0001-\u0001-\u0003-\u02ff"+
		"\b-\u0001.\u0001.\u0001.\u0001.\u0001.\u0001.\u0001.\u0001.\u0001.\u0003"+
		".\u030a\b.\u0001/\u0001/\u0001/\u0001/\u0001/\u0001/\u0001/\u0001/\u0001"+
		"/\u0001/\u0005/\u0316\b/\n/\f/\u0319\t/\u0001/\u0001/\u0001/\u00010\u0001"+
		"0\u00010\u00010\u00010\u00010\u00010\u00010\u00010\u00010\u00030\u0328"+
		"\b0\u00011\u00011\u00011\u00051\u032d\b1\n1\f1\u0330\t1\u00011\u00011"+
		"\u00011\u00012\u00012\u00012\u00012\u00012\u00012\u00013\u00013\u0001"+
		"3\u00013\u00033\u033f\b3\u00014\u00014\u00014\u00014\u00014\u00014\u0001"+
		"4\u00014\u00034\u0349\b4\u00015\u00015\u00015\u00015\u00015\u00015\u0001"+
		"6\u00016\u00016\u00016\u00016\u00016\u00016\u00016\u00016\u00016\u0004"+
		"6\u035b\b6\u000b6\f6\u035c\u00017\u00017\u00017\u00017\u00017\u00017\u0001"+
		"7\u00017\u00017\u00017\u00047\u0369\b7\u000b7\f7\u036a\u00018\u00018\u0001"+
		"8\u00018\u00018\u00019\u00019\u00019\u00059\u0375\b9\n9\f9\u0378\t9\u0001"+
		"9\u00019\u0001:\u0001:\u0001:\u0001:\u0001:\u0001:\u0001:\u0003:\u0383"+
		"\b:\u0001;\u0001;\u0001;\u0001;\u0001;\u0001;\u0001;\u0001<\u0001<\u0001"+
		"<\u0001<\u0001<\u0001<\u0001=\u0001=\u0001=\u0001=\u0001=\u0001=\u0001"+
		"=\u0001=\u0001=\u0005=\u039b\b=\n=\f=\u039e\t=\u0001>\u0001>\u0001>\u0001"+
		">\u0001>\u0001>\u0001>\u0001>\u0001>\u0001>\u0005>\u03aa\b>\n>\f>\u03ad"+
		"\t>\u0001?\u0001?\u0001?\u0001?\u0001?\u0001?\u0001?\u0001?\u0001?\u0001"+
		"?\u0001?\u0001?\u0001?\u0001?\u0001?\u0001?\u0001?\u0001?\u0003?\u03c1"+
		"\b?\u0001@\u0001@\u0001@\u0001@\u0001@\u0003@\u03c8\b@\u0001@\u0001@\u0001"+
		"@\u0003@\u03cd\b@\u0005@\u03cf\b@\n@\f@\u03d2\t@\u0001@\u0001@\u0001@"+
		"\u0001A\u0001A\u0001A\u0001A\u0001A\u0001A\u0001A\u0001A\u0001A\u0001"+
		"A\u0003A\u03e1\bA\u0001B\u0001B\u0001B\u0001B\u0001B\u0001B\u0001B\u0001"+
		"B\u0001B\u0005B\u03ec\bB\nB\fB\u03ef\tB\u0001B\u0001B\u0001B\u0001C\u0001"+
		"C\u0001C\u0001C\u0003C\u03f8\bC\u0001C\u0001C\u0001C\u0001D\u0001D\u0001"+
		"D\u0001D\u0001D\u0001D\u0001D\u0005D\u0404\bD\nD\fD\u0407\tD\u0001E\u0001"+
		"E\u0001E\u0001E\u0001E\u0003E\u040e\bE\u0001E\u0001E\u0001E\u0001F\u0001"+
		"F\u0001F\u0001F\u0001F\u0001F\u0001F\u0003F\u041a\bF\u0001F\u0001F\u0001"+
		"F\u0001G\u0001G\u0001G\u0001G\u0001G\u0001G\u0001G\u0005G\u0426\bG\nG"+
		"\fG\u0429\tG\u0001G\u0001G\u0001G\u0001H\u0001H\u0001H\u0001H\u0001H\u0001"+
		"H\u0005H\u0434\bH\nH\fH\u0437\tH\u0001I\u0001I\u0001I\u0001I\u0001I\u0005"+
		"I\u043e\bI\nI\fI\u0441\tI\u0003I\u0443\bI\u0001J\u0001J\u0001J\u0001J"+
		"\u0001J\u0003J\u044a\bJ\u0001J\u0001J\u0001J\u0001J\u0003J\u0450\bJ\u0005"+
		"J\u0452\bJ\nJ\fJ\u0455\tJ\u0001J\u0001J\u0001J\u0001K\u0001K\u0001L\u0001"+
		"L\u0001L\u0001L\u0001L\u0001L\u0001L\u0001L\u0005L\u0464\bL\nL\fL\u0467"+
		"\tL\u0001M\u0001M\u0001N\u0005N\u046c\bN\nN\fN\u046f\tN\u0001N\u0000\u0000"+
		"O\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a"+
		"\u001c\u001e \"$&(*,.02468:<>@BDFHJLNPRTVXZ\\^`bdfhjlnprtvxz|~\u0080\u0082"+
		"\u0084\u0086\u0088\u008a\u008c\u008e\u0090\u0092\u0094\u0096\u0098\u009a"+
		"\u009c\u0000\b\u0002\u0000\u000f\u000f\u0011\u0013\u0001\u0000 !\u0003"+
		"\u0000\b\b\u0014\u0015-/\u0001\u0000%&\u0002\u0000\u0003\u0003??\u0002"+
		"\u0000\u001d\u001d45\u0004\u0000 \"2245>>\u0002\u0000\u000f\u000f\u0011"+
		"\u0011\u04d8\u0000\u009e\u0001\u0000\u0000\u0000\u0002\u00dd\u0001\u0000"+
		"\u0000\u0000\u0004\u0105\u0001\u0000\u0000\u0000\u0006\u0123\u0001\u0000"+
		"\u0000\u0000\b\u0125\u0001\u0000\u0000\u0000\n\u0127\u0001\u0000\u0000"+
		"\u0000\f\u0137\u0001\u0000\u0000\u0000\u000e\u0154\u0001\u0000\u0000\u0000"+
		"\u0010\u0156\u0001\u0000\u0000\u0000\u0012\u0158\u0001\u0000\u0000\u0000"+
		"\u0014\u0164\u0001\u0000\u0000\u0000\u0016\u0167\u0001\u0000\u0000\u0000"+
		"\u0018\u017e\u0001\u0000\u0000\u0000\u001a\u0182\u0001\u0000\u0000\u0000"+
		"\u001c\u018c\u0001\u0000\u0000\u0000\u001e\u019f\u0001\u0000\u0000\u0000"+
		" \u01b8\u0001\u0000\u0000\u0000\"\u01bb\u0001\u0000\u0000\u0000$\u01c1"+
		"\u0001\u0000\u0000\u0000&\u01c5\u0001\u0000\u0000\u0000(\u01c8\u0001\u0000"+
		"\u0000\u0000*\u01cc\u0001\u0000\u0000\u0000,\u01cf\u0001\u0000\u0000\u0000"+
		".\u01e2\u0001\u0000\u0000\u00000\u01ea\u0001\u0000\u0000\u00002\u01f9"+
		"\u0001\u0000\u0000\u00004\u01fd\u0001\u0000\u0000\u00006\u0209\u0001\u0000"+
		"\u0000\u00008\u0213\u0001\u0000\u0000\u0000:\u021e\u0001\u0000\u0000\u0000"+
		"<\u0237\u0001\u0000\u0000\u0000>\u023d\u0001\u0000\u0000\u0000@\u0258"+
		"\u0001\u0000\u0000\u0000B\u025a\u0001\u0000\u0000\u0000D\u0261\u0001\u0000"+
		"\u0000\u0000F\u0265\u0001\u0000\u0000\u0000H\u028a\u0001\u0000\u0000\u0000"+
		"J\u028c\u0001\u0000\u0000\u0000L\u0292\u0001\u0000\u0000\u0000N\u0298"+
		"\u0001\u0000\u0000\u0000P\u029c\u0001\u0000\u0000\u0000R\u02a1\u0001\u0000"+
		"\u0000\u0000T\u02a6\u0001\u0000\u0000\u0000V\u02ab\u0001\u0000\u0000\u0000"+
		"X\u02f1\u0001\u0000\u0000\u0000Z\u02f3\u0001\u0000\u0000\u0000\\\u0309"+
		"\u0001\u0000\u0000\u0000^\u030b\u0001\u0000\u0000\u0000`\u0327\u0001\u0000"+
		"\u0000\u0000b\u0329\u0001\u0000\u0000\u0000d\u0334\u0001\u0000\u0000\u0000"+
		"f\u033e\u0001\u0000\u0000\u0000h\u0348\u0001\u0000\u0000\u0000j\u034a"+
		"\u0001\u0000\u0000\u0000l\u0350\u0001\u0000\u0000\u0000n\u035e\u0001\u0000"+
		"\u0000\u0000p\u036c\u0001\u0000\u0000\u0000r\u0371\u0001\u0000\u0000\u0000"+
		"t\u0382\u0001\u0000\u0000\u0000v\u0384\u0001\u0000\u0000\u0000x\u038b"+
		"\u0001\u0000\u0000\u0000z\u0391\u0001\u0000\u0000\u0000|\u039f\u0001\u0000"+
		"\u0000\u0000~\u03c0\u0001\u0000\u0000\u0000\u0080\u03c2\u0001\u0000\u0000"+
		"\u0000\u0082\u03e0\u0001\u0000\u0000\u0000\u0084\u03e2\u0001\u0000\u0000"+
		"\u0000\u0086\u03f3\u0001\u0000\u0000\u0000\u0088\u03fc\u0001\u0000\u0000"+
		"\u0000\u008a\u0408\u0001\u0000\u0000\u0000\u008c\u0412\u0001\u0000\u0000"+
		"\u0000\u008e\u041e\u0001\u0000\u0000\u0000\u0090\u042d\u0001\u0000\u0000"+
		"\u0000\u0092\u0442\u0001\u0000\u0000\u0000\u0094\u0444\u0001\u0000\u0000"+
		"\u0000\u0096\u0459\u0001\u0000\u0000\u0000\u0098\u045b\u0001\u0000\u0000"+
		"\u0000\u009a\u0468\u0001\u0000\u0000\u0000\u009c\u046d\u0001\u0000\u0000"+
		"\u0000\u009e\u00a8\u0003\u009cN\u0000\u009f\u00a3\u0003\u0002\u0001\u0000"+
		"\u00a0\u00a2\u0007\u0000\u0000\u0000\u00a1\u00a0\u0001\u0000\u0000\u0000"+
		"\u00a2\u00a5\u0001\u0000\u0000\u0000\u00a3\u00a1\u0001\u0000\u0000\u0000"+
		"\u00a3\u00a4\u0001\u0000\u0000\u0000\u00a4\u00a7\u0001\u0000\u0000\u0000"+
		"\u00a5\u00a3\u0001\u0000\u0000\u0000\u00a6\u009f\u0001\u0000\u0000\u0000"+
		"\u00a7\u00aa\u0001\u0000\u0000\u0000\u00a8\u00a6\u0001\u0000\u0000\u0000"+
		"\u00a8\u00a9\u0001\u0000\u0000\u0000\u00a9\u00b4\u0001\u0000\u0000\u0000"+
		"\u00aa\u00a8\u0001\u0000\u0000\u0000\u00ab\u00af\u0003\u0004\u0002\u0000"+
		"\u00ac\u00ae\u0007\u0000\u0000\u0000\u00ad\u00ac\u0001\u0000\u0000\u0000"+
		"\u00ae\u00b1\u0001\u0000\u0000\u0000\u00af\u00ad\u0001\u0000\u0000\u0000"+
		"\u00af\u00b0\u0001\u0000\u0000\u0000\u00b0\u00b3\u0001\u0000\u0000\u0000"+
		"\u00b1\u00af\u0001\u0000\u0000\u0000\u00b2\u00ab\u0001\u0000\u0000\u0000"+
		"\u00b3\u00b6\u0001\u0000\u0000\u0000\u00b4\u00b2\u0001\u0000\u0000\u0000"+
		"\u00b4\u00b5\u0001\u0000\u0000\u0000\u00b5\u00c0\u0001\u0000\u0000\u0000"+
		"\u00b6\u00b4\u0001\u0000\u0000\u0000\u00b7\u00bb\u0003\u0006\u0003\u0000"+
		"\u00b8\u00ba\u0007\u0000\u0000\u0000\u00b9\u00b8\u0001\u0000\u0000\u0000"+
		"\u00ba\u00bd\u0001\u0000\u0000\u0000\u00bb\u00b9\u0001\u0000\u0000\u0000"+
		"\u00bb\u00bc\u0001\u0000\u0000\u0000\u00bc\u00bf\u0001\u0000\u0000\u0000"+
		"\u00bd\u00bb\u0001\u0000\u0000\u0000\u00be\u00b7\u0001\u0000\u0000\u0000"+
		"\u00bf\u00c2\u0001\u0000\u0000\u0000\u00c0\u00be\u0001\u0000\u0000\u0000"+
		"\u00c0\u00c1\u0001\u0000\u0000\u0000\u00c1\u00cc\u0001\u0000\u0000\u0000"+
		"\u00c2\u00c0\u0001\u0000\u0000\u0000\u00c3\u00c7\u0003`0\u0000\u00c4\u00c6"+
		"\u0007\u0000\u0000\u0000\u00c5\u00c4\u0001\u0000\u0000\u0000\u00c6\u00c9"+
		"\u0001\u0000\u0000\u0000\u00c7\u00c5\u0001\u0000\u0000\u0000\u00c7\u00c8"+
		"\u0001\u0000\u0000\u0000\u00c8\u00cb\u0001\u0000\u0000\u0000\u00c9\u00c7"+
		"\u0001\u0000\u0000\u0000\u00ca\u00c3\u0001\u0000\u0000\u0000\u00cb\u00ce"+
		"\u0001\u0000\u0000\u0000\u00cc\u00ca\u0001\u0000\u0000\u0000\u00cc\u00cd"+
		"\u0001\u0000\u0000\u0000\u00cd\u00cf\u0001\u0000\u0000\u0000\u00ce\u00cc"+
		"\u0001\u0000\u0000\u0000\u00cf\u00d0\u0005\u0000\u0000\u0001\u00d0\u0001"+
		"\u0001\u0000\u0000\u0000\u00d1\u00d2\u0005\u001b\u0000\u0000\u00d2\u00d3"+
		"\u0003\u009cN\u0000\u00d3\u00d4\u0005,\u0000\u0000\u00d4\u00d5\u0003\u009c"+
		"N\u0000\u00d5\u00d6\u0005\u0007\u0000\u0000\u00d6\u00d7\u0003\u009cN\u0000"+
		"\u00d7\u00de\u0001\u0000\u0000\u0000\u00d8\u00d9\u0005\u001b\u0000\u0000"+
		"\u00d9\u00da\u0003\u009cN\u0000\u00da\u00db\u0005,\u0000\u0000\u00db\u00dc"+
		"\u0003\u009cN\u0000\u00dc\u00de\u0001\u0000\u0000\u0000\u00dd\u00d1\u0001"+
		"\u0000\u0000\u0000\u00dd\u00d8\u0001\u0000\u0000\u0000\u00de\u0003\u0001"+
		"\u0000\u0000\u0000\u00df\u00e0\u0005\u0017\u0000\u0000\u00e0\u00e3\u0003"+
		"\u009cN\u0000\u00e1\u00e4\u0005,\u0000\u0000\u00e2\u00e4\u0003t:\u0000"+
		"\u00e3\u00e1\u0001\u0000\u0000\u0000\u00e3\u00e2\u0001\u0000\u0000\u0000"+
		"\u00e4\u00e5\u0001\u0000\u0000\u0000\u00e5\u00e6\u0003\u009cN\u0000\u00e6"+
		"\u00e7\u0003\f\u0006\u0000\u00e7\u00e8\u0005\u0007\u0000\u0000\u00e8\u00e9"+
		"\u0003\u009cN\u0000\u00e9\u0106\u0001\u0000\u0000\u0000\u00ea\u00eb\u0005"+
		"\u0017\u0000\u0000\u00eb\u00ee\u0003\u009cN\u0000\u00ec\u00ef\u0005,\u0000"+
		"\u0000\u00ed\u00ef\u0003t:\u0000\u00ee\u00ec\u0001\u0000\u0000\u0000\u00ee"+
		"\u00ed\u0001\u0000\u0000\u0000\u00ef\u00f0\u0001\u0000\u0000\u0000\u00f0"+
		"\u00f1\u0003\u009cN\u0000\u00f1\u00f2\u0005\u0007\u0000\u0000\u00f2\u00f3"+
		"\u0003\u009cN\u0000\u00f3\u0106\u0001\u0000\u0000\u0000\u00f4\u00f5\u0005"+
		"\u0017\u0000\u0000\u00f5\u00f8\u0003\u009cN\u0000\u00f6\u00f9\u0005,\u0000"+
		"\u0000\u00f7\u00f9\u0003t:\u0000\u00f8\u00f6\u0001\u0000\u0000\u0000\u00f8"+
		"\u00f7\u0001\u0000\u0000\u0000\u00f9\u00fa\u0001\u0000\u0000\u0000\u00fa"+
		"\u00fb\u0003\u009cN\u0000\u00fb\u00fc\u0003\f\u0006\u0000\u00fc\u0106"+
		"\u0001\u0000\u0000\u0000\u00fd\u00fe\u0005\u0017\u0000\u0000\u00fe\u0101"+
		"\u0003\u009cN\u0000\u00ff\u0102\u0005,\u0000\u0000\u0100\u0102\u0003t"+
		":\u0000\u0101\u00ff\u0001\u0000\u0000\u0000\u0101\u0100\u0001\u0000\u0000"+
		"\u0000\u0102\u0103\u0001\u0000\u0000\u0000\u0103\u0104\u0003\u009cN\u0000"+
		"\u0104\u0106\u0001\u0000\u0000\u0000\u0105\u00df\u0001\u0000\u0000\u0000"+
		"\u0105\u00ea\u0001\u0000\u0000\u0000\u0105\u00f4\u0001\u0000\u0000\u0000"+
		"\u0105\u00fd\u0001\u0000\u0000\u0000\u0106\u0005\u0001\u0000\u0000\u0000"+
		"\u0107\u0108\u0005\u001a\u0000\u0000\u0108\u010c\u0003\u009cN\u0000\u0109"+
		"\u010a\u0003\b\u0004\u0000\u010a\u010b\u0003\u009cN\u0000\u010b\u010d"+
		"\u0001\u0000\u0000\u0000\u010c\u0109\u0001\u0000\u0000\u0000\u010c\u010d"+
		"\u0001\u0000\u0000\u0000\u010d\u0110\u0001\u0000\u0000\u0000\u010e\u0111"+
		"\u0005,\u0000\u0000\u010f\u0111\u0003t:\u0000\u0110\u010e\u0001\u0000"+
		"\u0000\u0000\u0110\u010f\u0001\u0000\u0000\u0000\u0111\u0112\u0001\u0000"+
		"\u0000\u0000\u0112\u0113\u0003\u009cN\u0000\u0113\u0114\u0005\u0007\u0000"+
		"\u0000\u0114\u0115\u0003\u009cN\u0000\u0115\u0124\u0001\u0000\u0000\u0000"+
		"\u0116\u0117\u0005\u001a\u0000\u0000\u0117\u011b\u0003\u009cN\u0000\u0118"+
		"\u0119\u0003\b\u0004\u0000\u0119\u011a\u0003\u009cN\u0000\u011a\u011c"+
		"\u0001\u0000\u0000\u0000\u011b\u0118\u0001\u0000\u0000\u0000\u011b\u011c"+
		"\u0001\u0000\u0000\u0000\u011c\u011f\u0001\u0000\u0000\u0000\u011d\u0120"+
		"\u0005,\u0000\u0000\u011e\u0120\u0003t:\u0000\u011f\u011d\u0001\u0000"+
		"\u0000\u0000\u011f\u011e\u0001\u0000\u0000\u0000\u0120\u0121\u0001\u0000"+
		"\u0000\u0000\u0121\u0122\u0003\u009cN\u0000\u0122\u0124\u0001\u0000\u0000"+
		"\u0000\u0123\u0107\u0001\u0000\u0000\u0000\u0123\u0116\u0001\u0000\u0000"+
		"\u0000\u0124\u0007\u0001\u0000\u0000\u0000\u0125\u0126\u0003\u009aM\u0000"+
		"\u0126\t\u0001\u0000\u0000\u0000\u0127\u0128\u0005\u0019\u0000\u0000\u0128"+
		"\u0129\u0003\u009cN\u0000\u0129\u012a\u0003\f\u0006\u0000\u012a\u012b"+
		"\u0003b1\u0000\u012b\u012c\u0003\u009cN\u0000\u012c\u000b\u0001\u0000"+
		"\u0000\u0000\u012d\u0134\u0003\u000e\u0007\u0000\u012e\u012f\u0005(\u0000"+
		"\u0000\u012f\u0130\u0003\u009cN\u0000\u0130\u0131\u0003\u000e\u0007\u0000"+
		"\u0131\u0133\u0001\u0000\u0000\u0000\u0132\u012e\u0001\u0000\u0000\u0000"+
		"\u0133\u0136\u0001\u0000\u0000\u0000\u0134\u0132\u0001\u0000\u0000\u0000"+
		"\u0134\u0135\u0001\u0000\u0000\u0000\u0135\u0138\u0001\u0000\u0000\u0000"+
		"\u0136\u0134\u0001\u0000\u0000\u0000\u0137\u012d\u0001\u0000\u0000\u0000"+
		"\u0137\u0138\u0001\u0000\u0000\u0000\u0138\u0139\u0001\u0000\u0000\u0000"+
		"\u0139\u013a\u0003\u009cN\u0000\u013a\r\u0001\u0000\u0000\u0000\u013b"+
		"\u013d\u0007\u0001\u0000\u0000\u013c\u013b\u0001\u0000\u0000\u0000\u013c"+
		"\u013d\u0001\u0000\u0000\u0000\u013d\u013e\u0001\u0000\u0000\u0000\u013e"+
		"\u013f\u0003\u009cN\u0000\u013f\u0140\u0003\u0010\b\u0000\u0140\u0147"+
		"\u0003\u009cN\u0000\u0141\u0142\u0005\"\u0000\u0000\u0142\u0143\u0003"+
		"\u009cN\u0000\u0143\u0144\u0003\u0012\t\u0000\u0144\u0146\u0001\u0000"+
		"\u0000\u0000\u0145\u0141\u0001\u0000\u0000\u0000\u0146\u0149\u0001\u0000"+
		"\u0000\u0000\u0147\u0145\u0001\u0000\u0000\u0000\u0147\u0148\u0001\u0000"+
		"\u0000\u0000\u0148\u0155\u0001\u0000\u0000\u0000\u0149\u0147\u0001\u0000"+
		"\u0000\u0000\u014a\u0151\u0003\u0012\t\u0000\u014b\u014c\u0005\"\u0000"+
		"\u0000\u014c\u014d\u0003\u009cN\u0000\u014d\u014e\u0003\u0012\t\u0000"+
		"\u014e\u0150\u0001\u0000\u0000\u0000\u014f\u014b\u0001\u0000\u0000\u0000"+
		"\u0150\u0153\u0001\u0000\u0000\u0000\u0151\u014f\u0001\u0000\u0000\u0000"+
		"\u0151\u0152\u0001\u0000\u0000\u0000\u0152\u0155\u0001\u0000\u0000\u0000"+
		"\u0153\u0151\u0001\u0000\u0000\u0000\u0154\u013c\u0001\u0000\u0000\u0000"+
		"\u0154\u014a\u0001\u0000\u0000\u0000\u0155\u000f\u0001\u0000\u0000\u0000"+
		"\u0156\u0157\u0003\u009aM\u0000\u0157\u0011\u0001\u0000\u0000\u0000\u0158"+
		"\u0159\u0005\u0003\u0000\u0000\u0159\u015a\u0003\u009cN\u0000\u015a\u015f"+
		"\u0003\u0014\n\u0000\u015b\u015c\u0005\t\u0000\u0000\u015c\u015d\u0003"+
		"\u009cN\u0000\u015d\u015e\u0003F#\u0000\u015e\u0160\u0001\u0000\u0000"+
		"\u0000\u015f\u015b\u0001\u0000\u0000\u0000\u015f\u0160\u0001\u0000\u0000"+
		"\u0000\u0160\u0161\u0001\u0000\u0000\u0000\u0161\u0162\u0005\u0004\u0000"+
		"\u0000\u0162\u0163\u0003\u009cN\u0000\u0163\u0013\u0001\u0000\u0000\u0000"+
		"\u0164\u0165\u0003\u009aM\u0000\u0165\u0166\u0003\u009cN\u0000\u0166\u0015"+
		"\u0001\u0000\u0000\u0000\u0167\u0168\u0005\u0018\u0000\u0000\u0168\u016a"+
		"\u0003\u009cN\u0000\u0169\u016b\u0003\u0018\f\u0000\u016a\u0169\u0001"+
		"\u0000\u0000\u0000\u016a\u016b\u0001\u0000\u0000\u0000\u016b\u016c\u0001"+
		"\u0000\u0000\u0000\u016c\u016d\u0005\u0005\u0000\u0000\u016d\u016f\u0003"+
		"\u009cN\u0000\u016e\u0170\u0003@ \u0000\u016f\u016e\u0001\u0000\u0000"+
		"\u0000\u016f\u0170\u0001\u0000\u0000\u0000\u0170\u0178\u0001\u0000\u0000"+
		"\u0000\u0171\u0172\u0005\u0007\u0000\u0000\u0172\u0174\u0003\u009cN\u0000"+
		"\u0173\u0175\u0003@ \u0000\u0174\u0173\u0001\u0000\u0000\u0000\u0174\u0175"+
		"\u0001\u0000\u0000\u0000\u0175\u0177\u0001\u0000\u0000\u0000\u0176\u0171"+
		"\u0001\u0000\u0000\u0000\u0177\u017a\u0001\u0000\u0000\u0000\u0178\u0176"+
		"\u0001\u0000\u0000\u0000\u0178\u0179\u0001\u0000\u0000\u0000\u0179\u017b"+
		"\u0001\u0000\u0000\u0000\u017a\u0178\u0001\u0000\u0000\u0000\u017b\u017c"+
		"\u0005\u0006\u0000\u0000\u017c\u017d\u0003\u009cN\u0000\u017d\u0017\u0001"+
		"\u0000\u0000\u0000\u017e\u017f\u0005\t\u0000\u0000\u017f\u0180\u0003\u009a"+
		"M\u0000\u0180\u0181\u0003\u009cN\u0000\u0181\u0019\u0001\u0000\u0000\u0000"+
		"\u0182\u0189\u0003\u001c\u000e\u0000\u0183\u0184\u0005(\u0000\u0000\u0184"+
		"\u0185\u0003\u009cN\u0000\u0185\u0186\u0003\u001c\u000e\u0000\u0186\u0188"+
		"\u0001\u0000\u0000\u0000\u0187\u0183\u0001\u0000\u0000\u0000\u0188\u018b"+
		"\u0001\u0000\u0000\u0000\u0189\u0187\u0001\u0000\u0000\u0000\u0189\u018a"+
		"\u0001\u0000\u0000\u0000\u018a\u001b\u0001\u0000\u0000\u0000\u018b\u0189"+
		"\u0001\u0000\u0000\u0000\u018c\u018d\u0003 \u0010\u0000\u018d\u0194\u0003"+
		"\u009cN\u0000\u018e\u018f\u0003\u001e\u000f\u0000\u018f\u0190\u0003 \u0010"+
		"\u0000\u0190\u0191\u0003\u009cN\u0000\u0191\u0193\u0001\u0000\u0000\u0000"+
		"\u0192\u018e\u0001\u0000\u0000\u0000\u0193\u0196\u0001\u0000\u0000\u0000"+
		"\u0194\u0192\u0001\u0000\u0000\u0000\u0194\u0195\u0001\u0000\u0000\u0000"+
		"\u0195\u001d\u0001\u0000\u0000\u0000\u0196\u0194\u0001\u0000\u0000\u0000"+
		"\u0197\u0198\u0005%\u0000\u0000\u0198\u01a0\u0003\u009cN\u0000\u0199\u019a"+
		"\u0005\'\u0000\u0000\u019a\u01a0\u0003\u009cN\u0000\u019b\u019c\u0005"+
		")\u0000\u0000\u019c\u01a0\u0003\u009cN\u0000\u019d\u019e\u0005\u0011\u0000"+
		"\u0000\u019e\u01a0\u0003\u009cN\u0000\u019f\u0197\u0001\u0000\u0000\u0000"+
		"\u019f\u0199\u0001\u0000\u0000\u0000\u019f\u019b\u0001\u0000\u0000\u0000"+
		"\u019f\u019d\u0001\u0000\u0000\u0000\u01a0\u001f\u0001\u0000\u0000\u0000"+
		"\u01a1\u01a4\u0003\"\u0011\u0000\u01a2\u01a4\u0003(\u0014\u0000\u01a3"+
		"\u01a1\u0001\u0000\u0000\u0000\u01a3\u01a2\u0001\u0000\u0000\u0000\u01a4"+
		"\u01ac\u0001\u0000\u0000\u0000\u01a5\u01ab\u0005\u0016\u0000\u0000\u01a6"+
		"\u01ab\u0003*\u0015\u0000\u01a7\u01ab\u0003,\u0016\u0000\u01a8\u01ab\u0003"+
		".\u0017\u0000\u01a9\u01ab\u00034\u001a\u0000\u01aa\u01a5\u0001\u0000\u0000"+
		"\u0000\u01aa\u01a6\u0001\u0000\u0000\u0000\u01aa\u01a7\u0001\u0000\u0000"+
		"\u0000\u01aa\u01a8\u0001\u0000\u0000\u0000\u01aa\u01a9\u0001\u0000\u0000"+
		"\u0000\u01ab\u01ae\u0001\u0000\u0000\u0000\u01ac\u01aa\u0001\u0000\u0000"+
		"\u0000\u01ac\u01ad\u0001\u0000\u0000\u0000\u01ad\u01b9\u0001\u0000\u0000"+
		"\u0000\u01ae\u01ac\u0001\u0000\u0000\u0000\u01af\u01b5\u0005\u0016\u0000"+
		"\u0000\u01b0\u01b5\u0003*\u0015\u0000\u01b1\u01b5\u0003,\u0016\u0000\u01b2"+
		"\u01b5\u0003.\u0017\u0000\u01b3\u01b5\u00034\u001a\u0000\u01b4\u01af\u0001"+
		"\u0000\u0000\u0000\u01b4\u01b0\u0001\u0000\u0000\u0000\u01b4\u01b1\u0001"+
		"\u0000\u0000\u0000\u01b4\u01b2\u0001\u0000\u0000\u0000\u01b4\u01b3\u0001"+
		"\u0000\u0000\u0000\u01b5\u01b6\u0001\u0000\u0000\u0000\u01b6\u01b4\u0001"+
		"\u0000\u0000\u0000\u01b6\u01b7\u0001\u0000\u0000\u0000\u01b7\u01b9\u0001"+
		"\u0000\u0000\u0000\u01b8\u01a3\u0001\u0000\u0000\u0000\u01b8\u01b4\u0001"+
		"\u0000\u0000\u0000\u01b9!\u0001\u0000\u0000\u0000\u01ba\u01bc\u0003$\u0012"+
		"\u0000\u01bb\u01ba\u0001\u0000\u0000\u0000\u01bb\u01bc\u0001\u0000\u0000"+
		"\u0000\u01bc\u01bd\u0001\u0000\u0000\u0000\u01bd\u01be\u0003&\u0013\u0000"+
		"\u01be#\u0001\u0000\u0000\u0000\u01bf\u01c2\u0003\u009aM\u0000\u01c0\u01c2"+
		"\u0005\u000b\u0000\u0000\u01c1\u01bf\u0001\u0000\u0000\u0000\u01c1\u01c0"+
		"\u0001\u0000\u0000\u0000\u01c1\u01c2\u0001\u0000\u0000\u0000\u01c2\u01c3"+
		"\u0001\u0000\u0000\u0000\u01c3\u01c4\u0005\r\u0000\u0000\u01c4%\u0001"+
		"\u0000\u0000\u0000\u01c5\u01c6\u0003\u009aM\u0000\u01c6\'\u0001\u0000"+
		"\u0000\u0000\u01c7\u01c9\u0003$\u0012\u0000\u01c8\u01c7\u0001\u0000\u0000"+
		"\u0000\u01c8\u01c9\u0001\u0000\u0000\u0000\u01c9\u01ca\u0001\u0000\u0000"+
		"\u0000\u01ca\u01cb\u0005\u000b\u0000\u0000\u01cb)\u0001\u0000\u0000\u0000"+
		"\u01cc\u01cd\u0005\n\u0000\u0000\u01cd\u01ce\u0003\u009aM\u0000\u01ce"+
		"+\u0001\u0000\u0000\u0000\u01cf\u01d0\u0005\u0001\u0000\u0000\u01d0\u01d2"+
		"\u0003\u009cN\u0000\u01d1\u01d3\u0003$\u0012\u0000\u01d2\u01d1\u0001\u0000"+
		"\u0000\u0000\u01d2\u01d3\u0001\u0000\u0000\u0000\u01d3\u01d4\u0001\u0000"+
		"\u0000\u0000\u01d4\u01d5\u0003\u009aM\u0000\u01d5\u01de\u0003\u009cN\u0000"+
		"\u01d6\u01d7\u0007\u0002\u0000\u0000\u01d7\u01da\u0003\u009cN\u0000\u01d8"+
		"\u01db\u0003\u009aM\u0000\u01d9\u01db\u0005,\u0000\u0000\u01da\u01d8\u0001"+
		"\u0000\u0000\u0000\u01da\u01d9\u0001\u0000\u0000\u0000\u01db\u01dc\u0001"+
		"\u0000\u0000\u0000\u01dc\u01dd\u0003\u009cN\u0000\u01dd\u01df\u0001\u0000"+
		"\u0000\u0000\u01de\u01d6\u0001\u0000\u0000\u0000\u01de\u01df\u0001\u0000"+
		"\u0000\u0000\u01df\u01e0\u0001\u0000\u0000\u0000\u01e0\u01e1\u0005\u0002"+
		"\u0000\u0000\u01e1-\u0001\u0000\u0000\u0000\u01e2\u01e4\u0005\t\u0000"+
		"\u0000\u01e3\u01e5\u0005\t\u0000\u0000\u01e4\u01e3\u0001\u0000\u0000\u0000"+
		"\u01e4\u01e5\u0001\u0000\u0000\u0000\u01e5\u01e8\u0001\u0000\u0000\u0000"+
		"\u01e6\u01e9\u0003\u009aM\u0000\u01e7\u01e9\u00030\u0018\u0000\u01e8\u01e6"+
		"\u0001\u0000\u0000\u0000\u01e8\u01e7\u0001\u0000\u0000\u0000\u01e9/\u0001"+
		"\u0000\u0000\u0000\u01ea\u01eb\u0005?\u0000\u0000\u01eb\u01ec\u0003\u009c"+
		"N\u0000\u01ec\u01ed\u00032\u0019\u0000\u01ed\u01ee\u0005\u0004\u0000\u0000"+
		"\u01ee1\u0001\u0000\u0000\u0000\u01ef\u01f7\u0005%\u0000\u0000\u01f0\u01f7"+
		"\u0005&\u0000\u0000\u01f1\u01f7\u0005#\u0000\u0000\u01f2\u01f7\u0005$"+
		"\u0000\u0000\u01f3\u01f7\u0005+\u0000\u0000\u01f4\u01f7\u0005,\u0000\u0000"+
		"\u01f5\u01f7\u0003\u009aM\u0000\u01f6\u01ef\u0001\u0000\u0000\u0000\u01f6"+
		"\u01f0\u0001\u0000\u0000\u0000\u01f6\u01f1\u0001\u0000\u0000\u0000\u01f6"+
		"\u01f2\u0001\u0000\u0000\u0000\u01f6\u01f3\u0001\u0000\u0000\u0000\u01f6"+
		"\u01f4\u0001\u0000\u0000\u0000\u01f6\u01f5\u0001\u0000\u0000\u0000\u01f7"+
		"\u01f8\u0001\u0000\u0000\u0000\u01f8\u01fa\u0003\u009cN\u0000\u01f9\u01f6"+
		"\u0001\u0000\u0000\u0000\u01fa\u01fb\u0001\u0000\u0000\u0000\u01fb\u01f9"+
		"\u0001\u0000\u0000\u0000\u01fb\u01fc\u0001\u0000\u0000\u0000\u01fc3\u0001"+
		"\u0000\u0000\u0000\u01fd\u01fe\u0005*\u0000\u0000\u01fe\u01ff\u0003\u009c"+
		"N\u0000\u01ff\u0200\u00036\u001b\u0000\u0200\u0201\u0003\u009cN\u0000"+
		"\u0201\u0202\u0005\u0004\u0000\u0000\u02025\u0001\u0000\u0000\u0000\u0203"+
		"\u020a\u0003\"\u0011\u0000\u0204\u020a\u0003(\u0014\u0000\u0205\u020a"+
		"\u0005\u0016\u0000\u0000\u0206\u020a\u0003*\u0015\u0000\u0207\u020a\u0003"+
		",\u0016\u0000\u0208\u020a\u0003.\u0017\u0000\u0209\u0203\u0001\u0000\u0000"+
		"\u0000\u0209\u0204\u0001\u0000\u0000\u0000\u0209\u0205\u0001\u0000\u0000"+
		"\u0000\u0209\u0206\u0001\u0000\u0000\u0000\u0209\u0207\u0001\u0000\u0000"+
		"\u0000\u0209\u0208\u0001\u0000\u0000\u0000\u020a7\u0001\u0000\u0000\u0000"+
		"\u020b\u020c\u0005\f\u0000\u0000\u020c\u0214\u0003\u009cN\u0000\u020d"+
		"\u020e\u0005(\u0000\u0000\u020e\u0214\u0003\u009cN\u0000\u020f\u0210\u0005"+
		"\u0011\u0000\u0000\u0210\u0214\u0003\u009cN\u0000\u0211\u0212\u0005\b"+
		"\u0000\u0000\u0212\u0214\u0003\u009cN\u0000\u0213\u020b\u0001\u0000\u0000"+
		"\u0000\u0213\u020d\u0001\u0000\u0000\u0000\u0213\u020f\u0001\u0000\u0000"+
		"\u0000\u0213\u0211\u0001\u0000\u0000\u0000\u02149\u0001\u0000\u0000\u0000"+
		"\u0215\u0216\u0003\u009aM\u0000\u0216\u0217\u0003\u009cN\u0000\u0217\u021f"+
		"\u0001\u0000\u0000\u0000\u0218\u0219\u0005<\u0000\u0000\u0219\u021f\u0003"+
		"\u009cN\u0000\u021a\u021b\u0005\u000b\u0000\u0000\u021b\u021f\u0003\u009a"+
		"M\u0000\u021c\u021d\u0005\u000e\u0000\u0000\u021d\u021f\u0003\u009aM\u0000"+
		"\u021e\u0215\u0001\u0000\u0000\u0000\u021e\u0218\u0001\u0000\u0000\u0000"+
		"\u021e\u021a\u0001\u0000\u0000\u0000\u021e\u021c\u0001\u0000\u0000\u0000"+
		"\u021f;\u0001\u0000\u0000\u0000\u0220\u0221\u0003\u001a\r\u0000\u0221"+
		"\u0222\u0005\u0005\u0000\u0000\u0222\u0224\u0003\u009cN\u0000\u0223\u0225"+
		"\u0003>\u001f\u0000\u0224\u0223\u0001\u0000\u0000\u0000\u0224\u0225\u0001"+
		"\u0000\u0000\u0000\u0225\u0226\u0001\u0000\u0000\u0000\u0226\u0227\u0005"+
		"\u0006\u0000\u0000\u0227\u0228\u0003\u009cN\u0000\u0228\u0238\u0001\u0000"+
		"\u0000\u0000\u0229\u022b\u0003X,\u0000\u022a\u0229\u0001\u0000\u0000\u0000"+
		"\u022b\u022e\u0001\u0000\u0000\u0000\u022c\u022a\u0001\u0000\u0000\u0000"+
		"\u022c\u022d\u0001\u0000\u0000\u0000\u022d\u022f\u0001\u0000\u0000\u0000"+
		"\u022e\u022c\u0001\u0000\u0000\u0000\u022f\u0230\u0005\u0005\u0000\u0000"+
		"\u0230\u0232\u0003\u009cN\u0000\u0231\u0233\u0003>\u001f\u0000\u0232\u0231"+
		"\u0001\u0000\u0000\u0000\u0232\u0233\u0001\u0000\u0000\u0000\u0233\u0234"+
		"\u0001\u0000\u0000\u0000\u0234\u0235\u0005\u0006\u0000\u0000\u0235\u0236"+
		"\u0003\u009cN\u0000\u0236\u0238\u0001\u0000\u0000\u0000\u0237\u0220\u0001"+
		"\u0000\u0000\u0000\u0237\u022c\u0001\u0000\u0000\u0000\u0238=\u0001\u0000"+
		"\u0000\u0000\u0239\u023a\u0005\u0007\u0000\u0000\u023a\u023c\u0003\u009c"+
		"N\u0000\u023b\u0239\u0001\u0000\u0000\u0000\u023c\u023f\u0001\u0000\u0000"+
		"\u0000\u023d\u023b\u0001\u0000\u0000\u0000\u023d\u023e\u0001\u0000\u0000"+
		"\u0000\u023e\u0240\u0001\u0000\u0000\u0000\u023f\u023d\u0001\u0000\u0000"+
		"\u0000\u0240\u0241\u0003@ \u0000\u0241\u0249\u0003\u009cN\u0000\u0242"+
		"\u0243\u0005\u0007\u0000\u0000\u0243\u0245\u0003\u009cN\u0000\u0244\u0246"+
		"\u0003@ \u0000\u0245\u0244\u0001\u0000\u0000\u0000\u0245\u0246\u0001\u0000"+
		"\u0000\u0000\u0246\u0248\u0001\u0000\u0000\u0000\u0247\u0242\u0001\u0000"+
		"\u0000\u0000\u0248\u024b\u0001\u0000\u0000\u0000\u0249\u0247\u0001\u0000"+
		"\u0000\u0000\u0249\u024a\u0001\u0000\u0000\u0000\u024a?\u0001\u0000\u0000"+
		"\u0000\u024b\u0249\u0001\u0000\u0000\u0000\u024c\u024d\u0003:\u001d\u0000"+
		"\u024d\u024e\u0005\t\u0000\u0000\u024e\u024f\u0003\u009cN\u0000\u024f"+
		"\u0251\u0003F#\u0000\u0250\u0252\u0003B!\u0000\u0251\u0250\u0001\u0000"+
		"\u0000\u0000\u0251\u0252\u0001\u0000\u0000\u0000\u0252\u0259\u0001\u0000"+
		"\u0000\u0000\u0253\u0254\u0003:\u001d\u0000\u0254\u0255\u0005\t\u0000"+
		"\u0000\u0255\u0256\u0003\u009cN\u0000\u0256\u0257\u0003D\"\u0000\u0257"+
		"\u0259\u0001\u0000\u0000\u0000\u0258\u024c\u0001\u0000\u0000\u0000\u0258"+
		"\u0253\u0001\u0000\u0000\u0000\u0259A\u0001\u0000\u0000\u0000\u025a\u025b"+
		"\u0005\u001c\u0000\u0000\u025b\u025c\u0003\u009cN\u0000\u025cC\u0001\u0000"+
		"\u0000\u0000\u025d\u0262\u0003X,\u0000\u025e\u0262\u0003^/\u0000\u025f"+
		"\u0260\u0005;\u0000\u0000\u0260\u0262\u0003\u009cN\u0000\u0261\u025d\u0001"+
		"\u0000\u0000\u0000\u0261\u025e\u0001\u0000\u0000\u0000\u0261\u025f\u0001"+
		"\u0000\u0000\u0000\u0262\u0263\u0001\u0000\u0000\u0000\u0263\u0261\u0001"+
		"\u0000\u0000\u0000\u0263\u0264\u0001\u0000\u0000\u0000\u0264E\u0001\u0000"+
		"\u0000\u0000\u0265\u026c\u0003H$\u0000\u0266\u0268\u00038\u001c\u0000"+
		"\u0267\u0266\u0001\u0000\u0000\u0000\u0267\u0268\u0001\u0000\u0000\u0000"+
		"\u0268\u0269\u0001\u0000\u0000\u0000\u0269\u026b\u0003H$\u0000\u026a\u0267"+
		"\u0001\u0000\u0000\u0000\u026b\u026e\u0001\u0000\u0000\u0000\u026c\u026a"+
		"\u0001\u0000\u0000\u0000\u026c\u026d\u0001\u0000\u0000\u0000\u026dG\u0001"+
		"\u0000\u0000\u0000\u026e\u026c\u0001\u0000\u0000\u0000\u026f\u0270\u0003"+
		"P(\u0000\u0270\u0271\u0003\u009cN\u0000\u0271\u028b\u0001\u0000\u0000"+
		"\u0000\u0272\u0273\u0003R)\u0000\u0273\u0274\u0003\u009cN\u0000\u0274"+
		"\u028b\u0001\u0000\u0000\u0000\u0275\u0276\u0003T*\u0000\u0276\u0277\u0003"+
		"\u009cN\u0000\u0277\u028b\u0001\u0000\u0000\u0000\u0278\u0279\u0005,\u0000"+
		"\u0000\u0279\u028b\u0003\u009cN\u0000\u027a\u027b\u0005\u001f\u0000\u0000"+
		"\u027b\u028b\u0003\u009cN\u0000\u027c\u027d\u0003\u009aM\u0000\u027d\u027e"+
		"\u0003\u009cN\u0000\u027e\u028b\u0001\u0000\u0000\u0000\u027f\u028b\u0003"+
		"v;\u0000\u0280\u0281\u0003t:\u0000\u0281\u0282\u0003\u009cN\u0000\u0282"+
		"\u028b\u0001\u0000\u0000\u0000\u0283\u028b\u0003N\'\u0000\u0284\u028b"+
		"\u0003x<\u0000\u0285\u028b\u0003J%\u0000\u0286\u0287\u0003V+\u0000\u0287"+
		"\u0288\u0003\u009cN\u0000\u0288\u028b\u0001\u0000\u0000\u0000\u0289\u028b"+
		"\u0003L&\u0000\u028a\u026f\u0001\u0000\u0000\u0000\u028a\u0272\u0001\u0000"+
		"\u0000\u0000\u028a\u0275\u0001\u0000\u0000\u0000\u028a\u0278\u0001\u0000"+
		"\u0000\u0000\u028a\u027a\u0001\u0000\u0000\u0000\u028a\u027c\u0001\u0000"+
		"\u0000\u0000\u028a\u027f\u0001\u0000\u0000\u0000\u028a\u0280\u0001\u0000"+
		"\u0000\u0000\u028a\u0283\u0001\u0000\u0000\u0000\u028a\u0284\u0001\u0000"+
		"\u0000\u0000\u028a\u0285\u0001\u0000\u0000\u0000\u028a\u0286\u0001\u0000"+
		"\u0000\u0000\u028a\u0289\u0001\u0000\u0000\u0000\u028bI\u0001\u0000\u0000"+
		"\u0000\u028c\u028d\u0005?\u0000\u0000\u028d\u028e\u0003\u009cN\u0000\u028e"+
		"\u028f\u0003F#\u0000\u028f\u0290\u0005\u0004\u0000\u0000\u0290\u0291\u0003"+
		"\u009cN\u0000\u0291K\u0001\u0000\u0000\u0000\u0292\u0293\u0005:\u0000"+
		"\u0000\u0293\u0294\u0003\u009cN\u0000\u0294\u0295\u0003F#\u0000\u0295"+
		"\u0296\u0005\u0004\u0000\u0000\u0296\u0297\u0003\u009cN\u0000\u0297M\u0001"+
		"\u0000\u0000\u0000\u0298\u0299\u0005\u0016\u0000\u0000\u0299\u029a\u0003"+
		"\u009cN\u0000\u029aO\u0001\u0000\u0000\u0000\u029b\u029d\u0007\u0003\u0000"+
		"\u0000\u029c\u029b\u0001\u0000\u0000\u0000\u029c\u029d\u0001\u0000\u0000"+
		"\u0000\u029d\u029e\u0001\u0000\u0000\u0000\u029e\u029f\u0005+\u0000\u0000"+
		"\u029fQ\u0001\u0000\u0000\u0000\u02a0\u02a2\u0007\u0003\u0000\u0000\u02a1"+
		"\u02a0\u0001\u0000\u0000\u0000\u02a1\u02a2\u0001\u0000\u0000\u0000\u02a2"+
		"\u02a3\u0001\u0000\u0000\u0000\u02a3\u02a4\u0005\u001d\u0000\u0000\u02a4"+
		"S\u0001\u0000\u0000\u0000\u02a5\u02a7\u0007\u0003\u0000\u0000\u02a6\u02a5"+
		"\u0001\u0000\u0000\u0000\u02a6\u02a7\u0001\u0000\u0000\u0000\u02a7\u02a8"+
		"\u0001\u0000\u0000\u0000\u02a8\u02a9\u0005#\u0000\u0000\u02a9U\u0001\u0000"+
		"\u0000\u0000\u02aa\u02ac\u0007\u0003\u0000\u0000\u02ab\u02aa\u0001\u0000"+
		"\u0000\u0000\u02ab\u02ac\u0001\u0000\u0000\u0000\u02ac\u02ad\u0001\u0000"+
		"\u0000\u0000\u02ad\u02ae\u0005$\u0000\u0000\u02aeW\u0001\u0000\u0000\u0000"+
		"\u02af\u02b0\u0003\u009aM\u0000\u02b0\u02b1\u0003\u009cN\u0000\u02b1\u02f2"+
		"\u0001\u0000\u0000\u0000\u02b2\u02b3\u0003P(\u0000\u02b3\u02b4\u0003\u009c"+
		"N\u0000\u02b4\u02f2\u0001\u0000\u0000\u0000\u02b5\u02b6\u0003R)\u0000"+
		"\u02b6\u02b7\u0003\u009cN\u0000\u02b7\u02f2\u0001\u0000\u0000\u0000\u02b8"+
		"\u02b9\u0003T*\u0000\u02b9\u02ba\u0003\u009cN\u0000\u02ba\u02f2\u0001"+
		"\u0000\u0000\u0000\u02bb\u02bc\u0003V+\u0000\u02bc\u02bd\u0003\u009cN"+
		"\u0000\u02bd\u02f2\u0001\u0000\u0000\u0000\u02be\u02bf\u0005,\u0000\u0000"+
		"\u02bf\u02f2\u0003\u009cN\u0000\u02c0\u02c1\u0003t:\u0000\u02c1\u02c2"+
		"\u0003\u009cN\u0000\u02c2\u02f2\u0001\u0000\u0000\u0000\u02c3\u02c4\u0005"+
		"\u0016\u0000\u0000\u02c4\u02f2\u0003\u009cN\u0000\u02c5\u02c6\u0005\u001f"+
		"\u0000\u0000\u02c6\u02f2\u0003\u009cN\u0000\u02c7\u02c8\u0005\u0014\u0000"+
		"\u0000\u02c8\u02f2\u0003\u009cN\u0000\u02c9\u02ca\u0005\u0015\u0000\u0000"+
		"\u02ca\u02f2\u0003\u009cN\u0000\u02cb\u02cc\u0005\t\u0000\u0000\u02cc"+
		"\u02f2\u0003\u009cN\u0000\u02cd\u02ce\u0005?\u0000\u0000\u02ce\u02d3\u0003"+
		"\u009cN\u0000\u02cf\u02d2\u0003X,\u0000\u02d0\u02d2\u0003\\.\u0000\u02d1"+
		"\u02cf\u0001\u0000\u0000\u0000\u02d1\u02d0\u0001\u0000\u0000\u0000\u02d2"+
		"\u02d5\u0001\u0000\u0000\u0000\u02d3\u02d1\u0001\u0000\u0000\u0000\u02d3"+
		"\u02d4\u0001\u0000\u0000\u0000\u02d4\u02d6\u0001\u0000\u0000\u0000\u02d5"+
		"\u02d3\u0001\u0000\u0000\u0000\u02d6\u02d7\u0005\u0004\u0000\u0000\u02d7"+
		"\u02d8\u0003\u009cN\u0000\u02d8\u02f2\u0001\u0000\u0000\u0000\u02d9\u02da"+
		"\u0005\u0003\u0000\u0000\u02da\u02df\u0003\u009cN\u0000\u02db\u02de\u0003"+
		"X,\u0000\u02dc\u02de\u0003\\.\u0000\u02dd\u02db\u0001\u0000\u0000\u0000"+
		"\u02dd\u02dc\u0001\u0000\u0000\u0000\u02de\u02e1\u0001\u0000\u0000\u0000"+
		"\u02df\u02dd\u0001\u0000\u0000\u0000\u02df\u02e0\u0001\u0000\u0000\u0000"+
		"\u02e0\u02e2\u0001\u0000\u0000\u0000\u02e1\u02df\u0001\u0000\u0000\u0000"+
		"\u02e2\u02e3\u0005\u0004\u0000\u0000\u02e3\u02e4\u0003\u009cN\u0000\u02e4"+
		"\u02f2\u0001\u0000\u0000\u0000\u02e5\u02e6\u0005\u0001\u0000\u0000\u02e6"+
		"\u02eb\u0003\u009cN\u0000\u02e7\u02ea\u0003X,\u0000\u02e8\u02ea\u0003"+
		"\\.\u0000\u02e9\u02e7\u0001\u0000\u0000\u0000\u02e9\u02e8\u0001\u0000"+
		"\u0000\u0000\u02ea\u02ed\u0001\u0000\u0000\u0000\u02eb\u02e9\u0001\u0000"+
		"\u0000\u0000\u02eb\u02ec\u0001\u0000\u0000\u0000\u02ec\u02ee\u0001\u0000"+
		"\u0000\u0000\u02ed\u02eb\u0001\u0000\u0000\u0000\u02ee\u02ef\u0005\u0002"+
		"\u0000\u0000\u02ef\u02f0\u0003\u009cN\u0000\u02f0\u02f2\u0001\u0000\u0000"+
		"\u0000\u02f1\u02af\u0001\u0000\u0000\u0000\u02f1\u02b2\u0001\u0000\u0000"+
		"\u0000\u02f1\u02b5\u0001\u0000\u0000\u0000\u02f1\u02b8\u0001\u0000\u0000"+
		"\u0000\u02f1\u02bb\u0001\u0000\u0000\u0000\u02f1\u02be\u0001\u0000\u0000"+
		"\u0000\u02f1\u02c0\u0001\u0000\u0000\u0000\u02f1\u02c3\u0001\u0000\u0000"+
		"\u0000\u02f1\u02c5\u0001\u0000\u0000\u0000\u02f1\u02c7\u0001\u0000\u0000"+
		"\u0000\u02f1\u02c9\u0001\u0000\u0000\u0000\u02f1\u02cb\u0001\u0000\u0000"+
		"\u0000\u02f1\u02cd\u0001\u0000\u0000\u0000\u02f1\u02d9\u0001\u0000\u0000"+
		"\u0000\u02f1\u02e5\u0001\u0000\u0000\u0000\u02f2Y\u0001\u0000\u0000\u0000"+
		"\u02f3\u02f4\u0005;\u0000\u0000\u02f4\u02f8\u0003\u009cN\u0000\u02f5\u02f7"+
		"\u0003X,\u0000\u02f6\u02f5\u0001\u0000\u0000\u0000\u02f7\u02fa\u0001\u0000"+
		"\u0000\u0000\u02f8\u02f6\u0001\u0000\u0000\u0000\u02f8\u02f9\u0001\u0000"+
		"\u0000\u0000\u02f9\u02fe\u0001\u0000\u0000\u0000\u02fa\u02f8\u0001\u0000"+
		"\u0000\u0000\u02fb\u02ff\u0003^/\u0000\u02fc\u02fd\u0005\u0007\u0000\u0000"+
		"\u02fd\u02ff\u0003\u009cN\u0000\u02fe\u02fb\u0001\u0000\u0000\u0000\u02fe"+
		"\u02fc\u0001\u0000\u0000\u0000\u02ff[\u0001\u0000\u0000\u0000\u0300\u030a"+
		"\u0003^/\u0000\u0301\u0302\u0005;\u0000\u0000\u0302\u030a\u0003\u009c"+
		"N\u0000\u0303\u0304\u0005\u0007\u0000\u0000\u0304\u030a\u0003\u009cN\u0000"+
		"\u0305\u0306\u0005\u0012\u0000\u0000\u0306\u030a\u0003\u009cN\u0000\u0307"+
		"\u0308\u0005\u0013\u0000\u0000\u0308\u030a\u0003\u009cN\u0000\u0309\u0300"+
		"\u0001\u0000\u0000\u0000\u0309\u0301\u0001\u0000\u0000\u0000\u0309\u0303"+
		"\u0001\u0000\u0000\u0000\u0309\u0305\u0001\u0000\u0000\u0000\u0309\u0307"+
		"\u0001\u0000\u0000\u0000\u030a]\u0001\u0000\u0000\u0000\u030b\u030c\u0005"+
		"\u0005\u0000\u0000\u030c\u0317\u0003\u009cN\u0000\u030d\u0316\u0003>\u001f"+
		"\u0000\u030e\u0316\u0003`0\u0000\u030f\u0316\u0003X,\u0000\u0310\u0316"+
		"\u0003^/\u0000\u0311\u0312\u0005;\u0000\u0000\u0312\u0316\u0003\u009c"+
		"N\u0000\u0313\u0314\u0005\u0007\u0000\u0000\u0314\u0316\u0003\u009cN\u0000"+
		"\u0315\u030d\u0001\u0000\u0000\u0000\u0315\u030e\u0001\u0000\u0000\u0000"+
		"\u0315\u030f\u0001\u0000\u0000\u0000\u0315\u0310\u0001\u0000\u0000\u0000"+
		"\u0315\u0311\u0001\u0000\u0000\u0000\u0315\u0313\u0001\u0000\u0000\u0000"+
		"\u0316\u0319\u0001\u0000\u0000\u0000\u0317\u0315\u0001\u0000\u0000\u0000"+
		"\u0317\u0318\u0001\u0000\u0000\u0000\u0318\u031a\u0001\u0000\u0000\u0000"+
		"\u0319\u0317\u0001\u0000\u0000\u0000\u031a\u031b\u0005\u0006\u0000\u0000"+
		"\u031b\u031c\u0003\u009cN\u0000\u031c_\u0001\u0000\u0000\u0000\u031d\u0328"+
		"\u0003<\u001e\u0000\u031e\u0328\u0003\n\u0005\u0000\u031f\u0328\u0003"+
		"\u0016\u000b\u0000\u0320\u0328\u0003\u0080@\u0000\u0321\u0328\u0003\u0084"+
		"B\u0000\u0322\u0328\u0003d2\u0000\u0323\u0328\u0003\u008aE\u0000\u0324"+
		"\u0328\u0003\u008cF\u0000\u0325\u0328\u0003\u008eG\u0000\u0326\u0328\u0003"+
		"Z-\u0000\u0327\u031d\u0001\u0000\u0000\u0000\u0327\u031e\u0001\u0000\u0000"+
		"\u0000\u0327\u031f\u0001\u0000\u0000\u0000\u0327\u0320\u0001\u0000\u0000"+
		"\u0000\u0327\u0321\u0001\u0000\u0000\u0000\u0327\u0322\u0001\u0000\u0000"+
		"\u0000\u0327\u0323\u0001\u0000\u0000\u0000\u0327\u0324\u0001\u0000\u0000"+
		"\u0000\u0327\u0325\u0001\u0000\u0000\u0000\u0327\u0326\u0001\u0000\u0000"+
		"\u0000\u0328a\u0001\u0000\u0000\u0000\u0329\u032a\u0005\u0005\u0000\u0000"+
		"\u032a\u032e\u0003\u009cN\u0000\u032b\u032d\u0003`0\u0000\u032c\u032b"+
		"\u0001\u0000\u0000\u0000\u032d\u0330\u0001\u0000\u0000\u0000\u032e\u032c"+
		"\u0001\u0000\u0000\u0000\u032e\u032f\u0001\u0000\u0000\u0000\u032f\u0331"+
		"\u0001\u0000\u0000\u0000\u0330\u032e\u0001\u0000\u0000\u0000\u0331\u0332"+
		"\u0005\u0006\u0000\u0000\u0332\u0333\u0003\u009cN\u0000\u0333c\u0001\u0000"+
		"\u0000\u0000\u0334\u0335\u00051\u0000\u0000\u0335\u0336\u0003\u009cN\u0000"+
		"\u0336\u0337\u0003f3\u0000\u0337\u0338\u0003\u009cN\u0000\u0338\u0339"+
		"\u0003b1\u0000\u0339e\u0001\u0000\u0000\u0000\u033a\u033f\u0003j5\u0000"+
		"\u033b\u033f\u0003l6\u0000\u033c\u033f\u0003n7\u0000\u033d\u033f\u0003"+
		"h4\u0000\u033e\u033a\u0001\u0000\u0000\u0000\u033e\u033b\u0001\u0000\u0000"+
		"\u0000\u033e\u033c\u0001\u0000\u0000\u0000\u033e\u033d\u0001\u0000\u0000"+
		"\u0000\u033fg\u0001\u0000\u0000\u0000\u0340\u0341\u0005\u0003\u0000\u0000"+
		"\u0341\u0342\u0003\u009cN\u0000\u0342\u0343\u0003f3\u0000\u0343\u0344"+
		"\u0003\u009cN\u0000\u0344\u0345\u0005\u0004\u0000\u0000\u0345\u0349\u0001"+
		"\u0000\u0000\u0000\u0346\u0349\u0003p8\u0000\u0347\u0349\u0003r9\u0000"+
		"\u0348\u0340\u0001\u0000\u0000\u0000\u0348\u0346\u0001\u0000\u0000\u0000"+
		"\u0348\u0347\u0001\u0000\u0000\u0000\u0349i\u0001\u0000\u0000\u0000\u034a"+
		"\u034b\u0005!\u0000\u0000\u034b\u034c\u0003\u009cN\u0000\u034c\u034d\u0005"+
		"\u0011\u0000\u0000\u034d\u034e\u0003\u009cN\u0000\u034e\u034f\u0003h4"+
		"\u0000\u034fk\u0001\u0000\u0000\u0000\u0350\u035a\u0003h4\u0000\u0351"+
		"\u0352\u0003\u009cN\u0000\u0352\u0353\u0005\u0011\u0000\u0000\u0353\u0354"+
		"\u0003\u009cN\u0000\u0354\u0355\u0005\"\u0000\u0000\u0355\u0356\u0003"+
		"\u009cN\u0000\u0356\u0357\u0005\u0011\u0000\u0000\u0357\u0358\u0003\u009c"+
		"N\u0000\u0358\u0359\u0003h4\u0000\u0359\u035b\u0001\u0000\u0000\u0000"+
		"\u035a\u0351\u0001\u0000\u0000\u0000\u035b\u035c\u0001\u0000\u0000\u0000"+
		"\u035c\u035a\u0001\u0000\u0000\u0000\u035c\u035d\u0001\u0000\u0000\u0000"+
		"\u035dm\u0001\u0000\u0000\u0000\u035e\u0368\u0003h4\u0000\u035f\u0360"+
		"\u0003\u009cN\u0000\u0360\u0361\u0005\u0011\u0000\u0000\u0361\u0362\u0003"+
		"\u009cN\u0000\u0362\u0363\u00052\u0000\u0000\u0363\u0364\u0003\u009cN"+
		"\u0000\u0364\u0365\u0005\u0011\u0000\u0000\u0365\u0366\u0003\u009cN\u0000"+
		"\u0366\u0367\u0003h4\u0000\u0367\u0369\u0001\u0000\u0000\u0000\u0368\u035f"+
		"\u0001\u0000\u0000\u0000\u0369\u036a\u0001\u0000\u0000\u0000\u036a\u0368"+
		"\u0001\u0000\u0000\u0000\u036a\u036b\u0001\u0000\u0000\u0000\u036bo\u0001"+
		"\u0000\u0000\u0000\u036c\u036d\u0005\u0003\u0000\u0000\u036d\u036e\u0003"+
		"\u009cN\u0000\u036e\u036f\u0003@ \u0000\u036f\u0370\u0005\u0004\u0000"+
		"\u0000\u0370q\u0001\u0000\u0000\u0000\u0371\u0376\u0007\u0004\u0000\u0000"+
		"\u0372\u0375\u0003X,\u0000\u0373\u0375\u0003\\.\u0000\u0374\u0372\u0001"+
		"\u0000\u0000\u0000\u0374\u0373\u0001\u0000\u0000\u0000\u0375\u0378\u0001"+
		"\u0000\u0000\u0000\u0376\u0374\u0001\u0000\u0000\u0000\u0376\u0377\u0001"+
		"\u0000\u0000\u0000\u0377\u0379\u0001\u0000\u0000\u0000\u0378\u0376\u0001"+
		"\u0000\u0000\u0000\u0379\u037a\u0005\u0004\u0000\u0000\u037as\u0001\u0000"+
		"\u0000\u0000\u037b\u037c\u0005\u001e\u0000\u0000\u037c\u037d\u0003\u009c"+
		"N\u0000\u037d\u037e\u0005,\u0000\u0000\u037e\u037f\u0003\u009cN\u0000"+
		"\u037f\u0380\u0005\u0004\u0000\u0000\u0380\u0383\u0001\u0000\u0000\u0000"+
		"\u0381\u0383\u0005\u0010\u0000\u0000\u0382\u037b\u0001\u0000\u0000\u0000"+
		"\u0382\u0381\u0001\u0000\u0000\u0000\u0383u\u0001\u0000\u0000\u0000\u0384"+
		"\u0385\u0005=\u0000\u0000\u0385\u0386\u0003\u009cN\u0000\u0386\u0387\u0005"+
		"<\u0000\u0000\u0387\u0388\u0003\u009cN\u0000\u0388\u0389\u0005\u0004\u0000"+
		"\u0000\u0389\u038a\u0003\u009cN\u0000\u038aw\u0001\u0000\u0000\u0000\u038b"+
		"\u038c\u00056\u0000\u0000\u038c\u038d\u0003\u009cN\u0000\u038d\u038e\u0003"+
		"z=\u0000\u038e\u038f\u0005\u0004\u0000\u0000\u038f\u0390\u0003\u009cN"+
		"\u0000\u0390y\u0001\u0000\u0000\u0000\u0391\u039c\u0003|>\u0000\u0392"+
		"\u0393\u0005\u0011\u0000\u0000\u0393\u0394\u0003\u009cN\u0000\u0394\u0395"+
		"\u0007\u0003\u0000\u0000\u0395\u0396\u0003\u009cN\u0000\u0396\u0397\u0005"+
		"\u0011\u0000\u0000\u0397\u0398\u0003\u009cN\u0000\u0398\u0399\u0003|>"+
		"\u0000\u0399\u039b\u0001\u0000\u0000\u0000\u039a\u0392\u0001\u0000\u0000"+
		"\u0000\u039b\u039e\u0001\u0000\u0000\u0000\u039c\u039a\u0001\u0000\u0000"+
		"\u0000\u039c\u039d\u0001\u0000\u0000\u0000\u039d{\u0001\u0000\u0000\u0000"+
		"\u039e\u039c\u0001\u0000\u0000\u0000\u039f\u03ab\u0003~?\u0000\u03a0\u03a1"+
		"\u0005\u000b\u0000\u0000\u03a1\u03a2\u0003\u009cN\u0000\u03a2\u03a3\u0003"+
		"~?\u0000\u03a3\u03aa\u0001\u0000\u0000\u0000\u03a4\u03a5\u0005\f\u0000"+
		"\u0000\u03a5\u03a6\u0003\u009cN\u0000\u03a6\u03a7\u0003P(\u0000\u03a7"+
		"\u03a8\u0003\u009cN\u0000\u03a8\u03aa\u0001\u0000\u0000\u0000\u03a9\u03a0"+
		"\u0001\u0000\u0000\u0000\u03a9\u03a4\u0001\u0000\u0000\u0000\u03aa\u03ad"+
		"\u0001\u0000\u0000\u0000\u03ab\u03a9\u0001\u0000\u0000\u0000\u03ab\u03ac"+
		"\u0001\u0000\u0000\u0000\u03ac}\u0001\u0000\u0000\u0000\u03ad\u03ab\u0001"+
		"\u0000\u0000\u0000\u03ae\u03af\u0003P(\u0000\u03af\u03b0\u0003\u009cN"+
		"\u0000\u03b0\u03c1\u0001\u0000\u0000\u0000\u03b1\u03b2\u0003T*\u0000\u03b2"+
		"\u03b3\u0003\u009cN\u0000\u03b3\u03c1\u0001\u0000\u0000\u0000\u03b4\u03b5"+
		"\u0003V+\u0000\u03b5\u03b6\u0003\u009cN\u0000\u03b6\u03c1\u0001\u0000"+
		"\u0000\u0000\u03b7\u03b8\u0003R)\u0000\u03b8\u03b9\u0003\u009cN\u0000"+
		"\u03b9\u03c1\u0001\u0000\u0000\u0000\u03ba\u03bb\u0005\u0003\u0000\u0000"+
		"\u03bb\u03bc\u0003\u009cN\u0000\u03bc\u03bd\u0003z=\u0000\u03bd\u03be"+
		"\u0005\u0004\u0000\u0000\u03be\u03bf\u0003\u009cN\u0000\u03bf\u03c1\u0001"+
		"\u0000\u0000\u0000\u03c0\u03ae\u0001\u0000\u0000\u0000\u03c0\u03b1\u0001"+
		"\u0000\u0000\u0000\u03c0\u03b4\u0001\u0000\u0000\u0000\u03c0\u03b7\u0001"+
		"\u0000\u0000\u0000\u03c0\u03ba\u0001\u0000\u0000\u0000\u03c1\u007f\u0001"+
		"\u0000\u0000\u0000\u03c2\u03c3\u00050\u0000\u0000\u03c3\u03c4\u0003\u009c"+
		"N\u0000\u03c4\u03c5\u0005\u0005\u0000\u0000\u03c5\u03c7\u0003\u009cN\u0000"+
		"\u03c6\u03c8\u0003\u0082A\u0000\u03c7\u03c6\u0001\u0000\u0000\u0000\u03c7"+
		"\u03c8\u0001\u0000\u0000\u0000\u03c8\u03d0\u0001\u0000\u0000\u0000\u03c9"+
		"\u03ca\u0005\u0007\u0000\u0000\u03ca\u03cc\u0003\u009cN\u0000\u03cb\u03cd"+
		"\u0003\u0082A\u0000\u03cc\u03cb\u0001\u0000\u0000\u0000\u03cc\u03cd\u0001"+
		"\u0000\u0000\u0000\u03cd\u03cf\u0001\u0000\u0000\u0000\u03ce\u03c9\u0001"+
		"\u0000\u0000\u0000\u03cf\u03d2\u0001\u0000\u0000\u0000\u03d0\u03ce\u0001"+
		"\u0000\u0000\u0000\u03d0\u03d1\u0001\u0000\u0000\u0000\u03d1\u03d3\u0001"+
		"\u0000\u0000\u0000\u03d2\u03d0\u0001\u0000\u0000\u0000\u03d3\u03d4\u0005"+
		"\u0006\u0000\u0000\u03d4\u03d5\u0003\u009cN\u0000\u03d5\u0081\u0001\u0000"+
		"\u0000\u0000\u03d6\u03d7\u0003:\u001d\u0000\u03d7\u03d8\u0005\t\u0000"+
		"\u0000\u03d8\u03d9\u0003\u009cN\u0000\u03d9\u03da\u0003F#\u0000\u03da"+
		"\u03e1\u0001\u0000\u0000\u0000\u03db\u03dc\u0003:\u001d\u0000\u03dc\u03dd"+
		"\u0005\t\u0000\u0000\u03dd\u03de\u0003\u009cN\u0000\u03de\u03df\u0003"+
		"D\"\u0000\u03df\u03e1\u0001\u0000\u0000\u0000\u03e0\u03d6\u0001\u0000"+
		"\u0000\u0000\u03e0\u03db\u0001\u0000\u0000\u0000\u03e1\u0083\u0001\u0000"+
		"\u0000\u0000\u03e2\u03e3\u00053\u0000\u0000\u03e3\u03e4\u0003\u009cN\u0000"+
		"\u03e4\u03e5\u0005\u0011\u0000\u0000\u03e5\u03e6\u0003\u009cN\u0000\u03e6"+
		"\u03e7\u0003\u009aM\u0000\u03e7\u03e8\u0003\u009cN\u0000\u03e8\u03e9\u0005"+
		"\u0005\u0000\u0000\u03e9\u03ed\u0003\u009cN\u0000\u03ea\u03ec\u0003\u0086"+
		"C\u0000\u03eb\u03ea\u0001\u0000\u0000\u0000\u03ec\u03ef\u0001\u0000\u0000"+
		"\u0000\u03ed\u03eb\u0001\u0000\u0000\u0000\u03ed\u03ee\u0001\u0000\u0000"+
		"\u0000\u03ee\u03f0\u0001\u0000\u0000\u0000\u03ef\u03ed\u0001\u0000\u0000"+
		"\u0000\u03f0\u03f1\u0005\u0006\u0000\u0000\u03f1\u03f2\u0003\u009cN\u0000"+
		"\u03f2\u0085\u0001\u0000\u0000\u0000\u03f3\u03f4\u0003\u0088D\u0000\u03f4"+
		"\u03f5\u0005\u0005\u0000\u0000\u03f5\u03f7\u0003\u009cN\u0000\u03f6\u03f8"+
		"\u0003>\u001f\u0000\u03f7\u03f6\u0001\u0000\u0000\u0000\u03f7\u03f8\u0001"+
		"\u0000\u0000\u0000\u03f8\u03f9\u0001\u0000\u0000\u0000\u03f9\u03fa\u0005"+
		"\u0006\u0000\u0000\u03fa\u03fb\u0003\u009cN\u0000\u03fb\u0087\u0001\u0000"+
		"\u0000\u0000\u03fc\u03fd\u0007\u0005\u0000\u0000\u03fd\u0405\u0003\u009c"+
		"N\u0000\u03fe\u03ff\u0005(\u0000\u0000\u03ff\u0400\u0003\u009cN\u0000"+
		"\u0400\u0401\u0007\u0005\u0000\u0000\u0401\u0402\u0003\u009cN\u0000\u0402"+
		"\u0404\u0001\u0000\u0000\u0000\u0403\u03fe\u0001\u0000\u0000\u0000\u0404"+
		"\u0407\u0001\u0000\u0000\u0000\u0405\u0403\u0001\u0000\u0000\u0000\u0405"+
		"\u0406\u0001\u0000\u0000\u0000\u0406\u0089\u0001\u0000\u0000\u0000\u0407"+
		"\u0405\u0001\u0000\u0000\u0000\u0408\u0409\u00057\u0000\u0000\u0409\u040a"+
		"\u0003\u009cN\u0000\u040a\u040b\u0005\u0005\u0000\u0000\u040b\u040d\u0003"+
		"\u009cN\u0000\u040c\u040e\u0003>\u001f\u0000\u040d\u040c\u0001\u0000\u0000"+
		"\u0000\u040d\u040e\u0001\u0000\u0000\u0000\u040e\u040f\u0001\u0000\u0000"+
		"\u0000\u040f\u0410\u0005\u0006\u0000\u0000\u0410\u0411\u0003\u009cN\u0000"+
		"\u0411\u008b\u0001\u0000\u0000\u0000\u0412\u0413\u00058\u0000\u0000\u0413"+
		"\u0414\u0003\u009cN\u0000\u0414\u0415\u0003\u009aM\u0000\u0415\u0416\u0003"+
		"\u009cN\u0000\u0416\u0417\u0005\u0005\u0000\u0000\u0417\u0419\u0003\u009c"+
		"N\u0000\u0418\u041a\u0003>\u001f\u0000\u0419\u0418\u0001\u0000\u0000\u0000"+
		"\u0419\u041a\u0001\u0000\u0000\u0000\u041a\u041b\u0001\u0000\u0000\u0000"+
		"\u041b\u041c\u0005\u0006\u0000\u0000\u041c\u041d\u0003\u009cN\u0000\u041d"+
		"\u008d\u0001\u0000\u0000\u0000\u041e\u041f\u00059\u0000\u0000\u041f\u0420"+
		"\u0003\u009cN\u0000\u0420\u0421\u0003\u0090H\u0000\u0421\u0422\u0003\u009c"+
		"N\u0000\u0422\u0423\u0005\u0005\u0000\u0000\u0423\u0427\u0003\u009cN\u0000"+
		"\u0424\u0426\u0003\u0094J\u0000\u0425\u0424\u0001\u0000\u0000\u0000\u0426"+
		"\u0429\u0001\u0000\u0000\u0000\u0427\u0425\u0001\u0000\u0000\u0000\u0427"+
		"\u0428\u0001\u0000\u0000\u0000\u0428\u042a\u0001\u0000\u0000\u0000\u0429"+
		"\u0427\u0001\u0000\u0000\u0000\u042a\u042b\u0005\u0006\u0000\u0000\u042b"+
		"\u042c\u0003\u009cN\u0000\u042c\u008f\u0001\u0000\u0000\u0000\u042d\u0435"+
		"\u0003\u0092I\u0000\u042e\u042f\u0003\u009cN\u0000\u042f\u0430\u0005("+
		"\u0000\u0000\u0430\u0431\u0003\u009cN\u0000\u0431\u0432\u0003\u0092I\u0000"+
		"\u0432\u0434\u0001\u0000\u0000\u0000\u0433\u042e\u0001\u0000\u0000\u0000"+
		"\u0434\u0437\u0001\u0000\u0000\u0000\u0435\u0433\u0001\u0000\u0000\u0000"+
		"\u0435\u0436\u0001\u0000\u0000\u0000\u0436\u0091\u0001\u0000\u0000\u0000"+
		"\u0437\u0435\u0001\u0000\u0000\u0000\u0438\u0443\u0005,\u0000\u0000\u0439"+
		"\u043f\u0003\u009aM\u0000\u043a\u043b\u0003\u009cN\u0000\u043b\u043c\u0003"+
		"\u009aM\u0000\u043c\u043e\u0001\u0000\u0000\u0000\u043d\u043a\u0001\u0000"+
		"\u0000\u0000\u043e\u0441\u0001\u0000\u0000\u0000\u043f\u043d\u0001\u0000"+
		"\u0000\u0000\u043f\u0440\u0001\u0000\u0000\u0000\u0440\u0443\u0001\u0000"+
		"\u0000\u0000\u0441\u043f\u0001\u0000\u0000\u0000\u0442\u0438\u0001\u0000"+
		"\u0000\u0000\u0442\u0439\u0001\u0000\u0000\u0000\u0443\u0093\u0001\u0000"+
		"\u0000\u0000\u0444\u0445\u0003\u0096K\u0000\u0445\u0446\u0003\u009cN\u0000"+
		"\u0446\u0447\u0005\u0005\u0000\u0000\u0447\u0449\u0003\u009cN\u0000\u0448"+
		"\u044a\u0003\u0098L\u0000\u0449\u0448\u0001\u0000\u0000\u0000\u0449\u044a"+
		"\u0001\u0000\u0000\u0000\u044a\u0453\u0001\u0000\u0000\u0000\u044b\u044c"+
		"\u0003\u009cN\u0000\u044c\u044d\u0005\u0007\u0000\u0000\u044d\u044f\u0003"+
		"\u009cN\u0000\u044e\u0450\u0003\u0098L\u0000\u044f\u044e\u0001\u0000\u0000"+
		"\u0000\u044f\u0450\u0001\u0000\u0000\u0000\u0450\u0452\u0001\u0000\u0000"+
		"\u0000\u0451\u044b\u0001\u0000\u0000\u0000\u0452\u0455\u0001\u0000\u0000"+
		"\u0000\u0453\u0451\u0001\u0000\u0000\u0000\u0453\u0454\u0001\u0000\u0000"+
		"\u0000\u0454\u0456\u0001\u0000\u0000\u0000\u0455\u0453\u0001\u0000\u0000"+
		"\u0000\u0456\u0457\u0005\u0006\u0000\u0000\u0457\u0458\u0003\u009cN\u0000"+
		"\u0458\u0095\u0001\u0000\u0000\u0000\u0459\u045a\u0005;\u0000\u0000\u045a"+
		"\u0097\u0001\u0000\u0000\u0000\u045b\u045c\u0003\u009aM\u0000\u045c\u045d"+
		"\u0003\u009cN\u0000\u045d\u045e\u0005\t\u0000\u0000\u045e\u045f\u0003"+
		"\u009cN\u0000\u045f\u0465\u0003P(\u0000\u0460\u0461\u0003\u009cN\u0000"+
		"\u0461\u0462\u0003P(\u0000\u0462\u0464\u0001\u0000\u0000\u0000\u0463\u0460"+
		"\u0001\u0000\u0000\u0000\u0464\u0467\u0001\u0000\u0000\u0000\u0465\u0463"+
		"\u0001\u0000\u0000\u0000\u0465\u0466\u0001\u0000\u0000\u0000\u0466\u0099"+
		"\u0001\u0000\u0000\u0000\u0467\u0465\u0001\u0000\u0000\u0000\u0468\u0469"+
		"\u0007\u0006\u0000\u0000\u0469\u009b\u0001\u0000\u0000\u0000\u046a\u046c"+
		"\u0007\u0007\u0000\u0000\u046b\u046a\u0001\u0000\u0000\u0000\u046c\u046f"+
		"\u0001\u0000\u0000\u0000\u046d\u046b\u0001\u0000\u0000\u0000\u046d\u046e"+
		"\u0001\u0000\u0000\u0000\u046e\u009d\u0001\u0000\u0000\u0000\u046f\u046d"+
		"\u0001\u0000\u0000\u0000q\u00a3\u00a8\u00af\u00b4\u00bb\u00c0\u00c7\u00cc"+
		"\u00dd\u00e3\u00ee\u00f8\u0101\u0105\u010c\u0110\u011b\u011f\u0123\u0134"+
		"\u0137\u013c\u0147\u0151\u0154\u015f\u016a\u016f\u0174\u0178\u0189\u0194"+
		"\u019f\u01a3\u01aa\u01ac\u01b4\u01b6\u01b8\u01bb\u01c1\u01c8\u01d2\u01da"+
		"\u01de\u01e4\u01e8\u01f6\u01fb\u0209\u0213\u021e\u0224\u022c\u0232\u0237"+
		"\u023d\u0245\u0249\u0251\u0258\u0261\u0263\u0267\u026c\u028a\u029c\u02a1"+
		"\u02a6\u02ab\u02d1\u02d3\u02dd\u02df\u02e9\u02eb\u02f1\u02f8\u02fe\u0309"+
		"\u0315\u0317\u0327\u032e\u033e\u0348\u035c\u036a\u0374\u0376\u0382\u039c"+
		"\u03a9\u03ab\u03c0\u03c7\u03cc\u03d0\u03e0\u03ed\u03f7\u0405\u040d\u0419"+
		"\u0427\u0435\u043f\u0442\u0449\u044f\u0453\u0465\u046d";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}