package com.json;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;

import com.json.listener.JSONLexerErrorListener;
import com.json.listener.JSONParserErrorListener;
import com.json.parser.JSONParser;


public class Main {
    public static void main(String[] args) {
        try {

            if(args.length == 0)
            {
                System.out.println("You need to specify the path for the json file");
                return;
            }

            JSONLexer lexer = new JSONLexer(CharStreams.fromFileName(args[0]));
            JSONLexerErrorListener lexerErrorListener = new JSONLexerErrorListener();
            lexer.addErrorListener(lexerErrorListener);
            

            TokenStream tokenStream = new CommonTokenStream(lexer);
            JSONParser parser = new JSONParser(tokenStream);

            parser.addErrorListener(new JSONParserErrorListener());
            parser.json();


            if(lexerErrorListener.foundErrors)
            {
                System.out.println("Invalid Json");
                return;
            }
            
            if (parser.getNumberOfSyntaxErrors() > 0) {
                System.out.println("Total syntax errors found: " + (parser.getNumberOfSyntaxErrors()));
                System.out.println("Invalid Json");
            } else {
                System.out.println("Valid Json");
            }


        } catch (Exception e) {
            System.out.println(e);
        }
    }
}