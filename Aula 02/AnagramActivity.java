package br.com.android.helloandroid;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AnagramActivity extends Activity {

    public void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.activity_anagramas);

        final EditText tb1 = (EditText) findViewById(R.id.text1);
        final EditText tb2 = (EditText) findViewById(R.id.text2);
        ((Button) findViewById(R.id.buttonCheck))
                .setOnClickListener(new Button.OnClickListener() {
                    public void onClick(final View arg0) {
                        Context context = getApplicationContext();
                        int duraaon = Toast.LENGTH_SHORT;
                        int textResourceId = R.string.nao_sao_anagramas;
                        if (anagrams(tb1.getText().toString(), tb2.getText().toString())) {
                            textResourceId = R.string.sao_anagramas;
                        }
                        Toast toast = Toast.makeText(context, textResourceId, duraaon);
                        toast.show();
                    }
                });
    }

    private boolean anagrams(final String s1,final String s2){
        boolean yes=true;
        if(s1.length()!=s2.length()){
            yes=false;
        }else{
            int[]table = new int[Character.MAX_VALUE];
            for(int i=0;i<s1.length();i++){
                table[s1.charAt(i)]++;
            }
            for(int i=0;i<s2.length();i++){
                if(table[s2.charAt(i)]==0){
                    yes=false;
                    break;
                }else{
                    table[s2.charAt(i)]--;
                }
            }
        }
        return yes;
    }
}




