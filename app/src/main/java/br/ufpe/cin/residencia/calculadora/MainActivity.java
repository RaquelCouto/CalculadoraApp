package br.ufpe.cin.residencia.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Importing the Id's of buttons

        Button btn0 = findViewById(R.id.btn_0);
        Button btn1 = findViewById(R.id.btn_1);
        Button btn2 = findViewById(R.id.btn_2);
        Button btn3 = findViewById(R.id.btn_3);
        Button btn4 = findViewById(R.id.btn_4);
        Button btn5 = findViewById(R.id.btn_5);
        Button btn6 = findViewById(R.id.btn_6);
        Button btn7 = findViewById(R.id.btn_7);
        Button btn8 = findViewById(R.id.btn_8);
        Button btn9 = findViewById(R.id.btn_9);
        Button btn_Dot = findViewById(R.id.btn_Dot);
        Button btn_Equal = findViewById(R.id.btn_Equal);
        Button btn_Lparent = findViewById(R.id.btn_LParen);
        Button btn_Rparent = findViewById(R.id.btn_RParen);
        Button btn_pot = findViewById(R.id.btn_Power);
        Button btn_div = findViewById(R.id.btn_Divide);
        Button btn_mult = findViewById(R.id.btn_Multiply);
        Button btn_subt = findViewById(R.id.btn_Subtract);
        Button btn_add = findViewById(R.id.btn_Add);
        Button btn_clear = findViewById(R.id.btn_Clear);
        Button btn_cos = findViewById(R.id.btn_cos);
        Button btn_sen = findViewById(R.id.btn_sen);
        Button btn_sqrt = findViewById(R.id.btn_sqrt);

        EditText expressao = findViewById(R.id.text_calc);
        TextView resultado = findViewById(R.id.text_info);

        /*
        * Adding actions to the buttons
        */

        btn0.setOnClickListener(
                v -> {
                    expressao.setText(expressao.getText().toString()+"0");
                }
        );

        btn1.setOnClickListener(
                v -> {
                    expressao.setText(expressao.getText().toString()+"1");
                }
        );

        btn2.setOnClickListener(
                v -> {
                    expressao.setText(expressao.getText().toString()+"2");
                }
        );

        btn3.setOnClickListener(
                v -> {
                    expressao.setText(expressao.getText().toString()+"3");
                }
        );

        btn4.setOnClickListener(
                v -> {
                    expressao.setText(expressao.getText().toString()+"4");
                }
        );

        btn5.setOnClickListener(
                v -> {
                    expressao.setText(expressao.getText().toString()+"5");
                }
        );

        btn6.setOnClickListener(
                v -> {
                    expressao.setText(expressao.getText().toString()+"6");
                }
        );

        btn7.setOnClickListener(
                v -> {
                    expressao.setText(expressao.getText().toString()+"7");
                }
        );

        btn7.setOnClickListener(
                v -> {
                    expressao.setText(expressao.getText().toString()+"7");
                }
        );

        btn8.setOnClickListener(
                v -> {
                    expressao.setText(expressao.getText().toString()+"8");
                }
        );

        btn9.setOnClickListener(
                v -> {
                    expressao.setText(expressao.getText().toString()+"9");
                }
        );

        btn9.setOnClickListener(
                v -> {
                    expressao.setText(expressao.getText().toString()+"9");
                }
        );

        btn_Dot.setOnClickListener(
                v -> {
                    expressao.setText(expressao.getText().toString()+".");
                }
        );

        btn_Lparent.setOnClickListener(
                v -> {
                    expressao.setText(expressao.getText().toString()+"(");
                }
        );

        btn_Rparent.setOnClickListener(
                v -> {
                    expressao.setText(expressao.getText().toString()+")");
                }
        );

        btn_pot.setOnClickListener(
                v -> {
                    expressao.setText(expressao.getText().toString()+"^");
                }
        );

        btn_div.setOnClickListener(
                v -> {
                    expressao.setText(expressao.getText().toString()+"/");
                }
        );

        btn_mult.setOnClickListener(
                v -> {
                    expressao.setText(expressao.getText().toString()+"*");
                }
        );

        btn_subt.setOnClickListener(
                v -> {
                    expressao.setText(expressao.getText().toString()+"-");
                }
        );

        btn_add.setOnClickListener(
                v -> {
                    expressao.setText(expressao.getText().toString()+"+");
                }
        );

        btn_Equal.setOnClickListener(
                v-> {
                    String conteudoAtual = expressao.getText().toString();
                    try{
                        double res = eval(conteudoAtual);
                        resultado.setText(String.valueOf(res));
                        expressao.setText("");
                    }
                    catch (RuntimeException e){
                        Context context = getApplicationContext();
                        CharSequence text = "Operação Inválida!";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                        expressao.setText("");
                    }
                }
        );

        btn_clear.setOnClickListener(
                v -> {
                    resultado.setText("");
                    expressao.setText("");
                }
        );

        btn_cos.setOnClickListener(
                v -> {
                    expressao.setText(expressao.getText().toString()+"cos");
                }
        );

        btn_sen.setOnClickListener(
                v -> {
                    expressao.setText(expressao.getText().toString()+"sin");
                }
        );

        btn_sqrt.setOnClickListener(
                v -> {
                    expressao.setText(expressao.getText().toString()+"sqrt");
                }
        );
    }



    //Como usar a função:
    // eval("2+2") == 4.0
    // eval("2+3*4") = 14.0
    // eval("(2+3)*4") = 20.0
    //Fonte: https://stackoverflow.com/a/26227947
    public static double eval(final String str) {
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < str.length()){
                    throw new RuntimeException("Caractere inesperado: " + (char)ch);
                }
                return x;
            }

            // Grammar:
            // expression = term | expression `+` term | expression `-` term
            // term = factor | term `*` factor | term `/` factor
            // factor = `+` factor | `-` factor | `(` expression `)`
            //        | number | functionName factor | factor `^` factor

            double parseExpression() {
                double x = parseTerm();
                for (;;) {
                    if      (eat('+')) x += parseTerm(); // adição
                    else if (eat('-')) x -= parseTerm(); // subtração
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (;;) {
                    if      (eat('*')) x *= parseFactor(); // multiplicação
                    else if (eat('/')) x /= parseFactor(); // divisão
                    else return x;
                }
            }

            double parseFactor() {
                if (eat('+')) return parseFactor(); // + unário
                if (eat('-')) return -parseFactor(); // - unário

                double x;
                int startPos = this.pos;
                if (eat('(')) { // parênteses
                    x = parseExpression();
                    eat(')');
                }
                else if ((ch >= '0' && ch <= '9') || ch == '.') { // números
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                }
                else if (ch >= 'a' && ch <= 'z') { // funções
                    while (ch >= 'a' && ch <= 'z') nextChar();
                    String func = str.substring(startPos, this.pos);
                    x = parseFactor();
                    if (func.equals("sqrt")) x = Math.sqrt(x);
                    else if (func.equals("sin")) x = Math.sin(Math.toRadians(x));
                    else if (func.equals("cos")) x = Math.cos(Math.toRadians(x));
                    else if (func.equals("tan")) x = Math.tan(Math.toRadians(x));
                    else throw new RuntimeException("Função desconhecida: " + func);
                }
                else {
                    throw new RuntimeException("Caractere inesperado: " + (char)ch);
                }

                if (eat('^')) x = Math.pow(x, parseFactor()); // potência

                return x;
            }
        }.parse();
    }
}
