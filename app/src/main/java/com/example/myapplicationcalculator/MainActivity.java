package com.example.myapplicationcalculator;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.List;
import java.util.ArrayList;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {
    public TextView sonuc, cozum;
    public Button bir, iki, uc, dort, bes, alti, yedi, sekiz, dokuz, sifir, virgul, bilinmeyen, esit, topla, cikar, bol, carpma, yuzde, cikis, ac;
    String girdi = "";

    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sonuc = findViewById(R.id.sonuc);
        cozum = findViewById(R.id.cozum);
        bol = findViewById(R.id.bol);
        topla = findViewById(R.id.topla);
        cikar = findViewById(R.id.cikar);
        carpma = findViewById(R.id.carpma);
        ac = findViewById(R.id.ac);
        bir = findViewById(R.id.bir);
        iki = findViewById(R.id.iki);
        uc = findViewById(R.id.uc);
        dort = findViewById(R.id.dort);
        bes = findViewById(R.id.bes);
        alti = findViewById(R.id.alti);
        yedi = findViewById(R.id.yedi);
        sekiz = findViewById(R.id.sekiz);
        dokuz = findViewById(R.id.dokuz);
        sifir = findViewById(R.id.sifir);
        virgul = findViewById(R.id.virgul);
        cikis = findViewById(R.id.exit);
        yuzde = findViewById(R.id.yuzde);
        esit = findViewById(R.id.esit);
        bilinmeyen = findViewById(R.id.undenified);

        cikis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Uygulamadan Çıkış")
                        .setMessage("Uygulamadan çıkmak istediğinize emin misiniz?")
                        .setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                finishAffinity();
                            }
                        })
                        .setNegativeButton("Hayır", null)
                        .show();
            }
        });

        yuzde.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!girdi.isEmpty()) {
                    float sonucDeger = Float.parseFloat(girdi);
                    float yuzdeSonuc = sonucDeger / 100.0f;
                    sonuc.setText(String.valueOf(yuzdeSonuc));
                    cozum.setText(sonucDeger + "% 100");
                    girdi = "";
                }
            }
        });

        virgul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                girdi += ".";
                sonuc.setText(girdi);
            }
        });

        sifir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                girdi += "0";
                sonuc.setText(girdi);
            }
        });

        bir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                girdi += "1";
                sonuc.setText(girdi);
            }
        });

        iki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                girdi += "2";
                sonuc.setText(girdi);
            }
        });

        uc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                girdi += "3";
                sonuc.setText(girdi);
            }
        });

        dort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                girdi += "4";
                sonuc.setText(girdi);
            }
        });

        bes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                girdi += "5";
                sonuc.setText(girdi);
            }
        });

        alti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                girdi += "6";
                sonuc.setText(girdi);
            }
        });

        yedi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                girdi += "7";
                sonuc.setText(girdi);
            }
        });

        sekiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                girdi += "8";
                sonuc.setText(girdi);
            }
        });

        dokuz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                girdi += "9";
                sonuc.setText(girdi);
            }
        });

        topla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                girdi += "+";
                sonuc.setText(girdi);
            }
        });

        cikar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                girdi += "-";
                sonuc.setText(girdi);
            }
        });

        carpma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                girdi += "*";
                sonuc.setText(girdi);
            }
        });

        bol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                girdi += "/";
                sonuc.setText(girdi);
            }
        });

        esit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] tokens = tokenizeExpression(girdi);
                String rpn = infixToRPN(tokens);
                float result = evaluateRPN(rpn);
                sonuc.setText(String.valueOf(result));
                cozum.setText(girdi + " = " + result);
                girdi = "";
            }
        });

        ac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                girdi = "";
                sonuc.setText("");
                cozum.setText("");
            }
        });
    }
    //Kodun buradan sonraki kısmını internetteki araştırmalarım sayesinde yaptım. Hesap işleminin sırasıyla yapılmasını ve istediğimiz kadar sayı eklenmesini stack
    //kullanarak yapabileceğimi farkettim. Bunun üzerine internette araştırmaya koyduldum ve karşıma "ters polonya notasyonu" yani RPN adlı bir metod çıktı. Bende
    //bunu elimden geldiğince öğrenip koduma entegre ettim. Bu metoda tamamiyle hakim olamadım ve yazarken internetten yardım almam gerekti ama hazır olarak
    //kütüphane kullanmak yerine en azından yeni bir metod öğrenmeye çalışıp bununla yapmak istedim. Çünkü kütüphane kullanınca çok kısa süreceğini öngördüm
    //ve bunun benim için pekte yararlı olmayacağını düşündüm.
    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }
    private boolean isDigitOrDot(char c) {
        return Character.isDigit(c) || c == '.';
    }
    private boolean isNumberStart(char c) {
        return Character.isDigit(c) || (c == '-' && girdi.isEmpty());
    }
    private String infixToRPN(String[] infixTokens) {
        StringBuilder rpn = new StringBuilder();
        Stack<Character> operatorStack = new Stack<>();
        for (String token : infixTokens) {
            char c = token.charAt(0);
            if (isNumberStart(c)) {
                rpn.append(token).append(' ');
            } else if (isOperator(c)) {
                while (!operatorStack.isEmpty() && precedence(operatorStack.peek()) >= precedence(c)) {
                    rpn.append(operatorStack.pop()).append(' ');
                }
                operatorStack.push(c);
            }
        }
        while (!operatorStack.isEmpty()) {
            rpn.append(operatorStack.pop()).append(' ');
        }
        return rpn.toString();
    }
    private String[] tokenizeExpression(String expression) {
        List<String> tokens = new ArrayList<>();
        StringBuilder currentNumber = new StringBuilder();

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (isOperator(c)) {
                if (currentNumber.length() > 0) {
                    tokens.add(currentNumber.toString());
                    currentNumber.setLength(0);
                }
                tokens.add(String.valueOf(c));
            } else if (isDigitOrDot(c)) {
                currentNumber.append(c);
            } else if (!Character.isWhitespace(c)) {
                throw new IllegalArgumentException("Tanımlanmayan ifade: " + c);
            }
        }

        if (currentNumber.length() > 0) {
            tokens.add(currentNumber.toString());
        }

        return tokens.toArray(new String[0]);
    }
    private int precedence(char operator) {
        switch (operator) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
        }
        return -1;
    }

    private float evaluateRPN(String rpn) {
        Stack<Float> operandStack = new Stack<>();
        String[] tokens = rpn.trim().split("\\s+");

        for (String token : tokens) {
            if (Character.isDigit(token.charAt(0))) {
                operandStack.push(Float.parseFloat(token));
            } else {
                float operand2 = operandStack.pop();
                float operand1 = operandStack.pop();
                float result;
                switch (token) {
                    case "+":
                        result = operand1 + operand2;
                        break;
                    case "-":
                        result = operand1 - operand2;
                        break;
                    case "*":
                        result = operand1 * operand2;
                        break;
                    case "/":
                        if (operand2 != 0)
                            result = operand1 / operand2;
                        else
                            throw new ArithmeticException("Sıfıra bölme hatası");
                        break;
                    default:
                        throw new IllegalArgumentException("Geçersiz token: " + token);
                }
                operandStack.push(result);
            }
        }
        return operandStack.pop();
    }
}
