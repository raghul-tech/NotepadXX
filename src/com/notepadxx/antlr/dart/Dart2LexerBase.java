package com.notepadxx.antlr.dart;
//import java.util.List;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Lexer;

public abstract class Dart2LexerBase extends Lexer
{
    protected Dart2LexerBase(CharStream input) {
        super(input);
    }

    protected boolean CheckNotOpenBrace()
    {
        return _input.LA(1) != '{';
    }
}
