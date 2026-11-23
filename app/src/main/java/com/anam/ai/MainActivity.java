package com.anam.ai;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout main = new LinearLayout(this);
        main.setOrientation(LinearLayout.VERTICAL);
        main.setPadding(24, 30, 24, 20);
        main.setBackgroundColor(Color.rgb(12, 10, 30));

        TextView title = new TextView(this);
        title.setText("ANAM AI");
        title.setTextSize(30);
        title.setTextColor(Color.rgb(190, 130, 255));
        title.setGravity(Gravity.CENTER);
        main.addView(title);

        TextView subtitle = new TextView(this);
        subtitle.setText("Yasmin - Your AI Companion");
        subtitle.setTextSize(16);
        subtitle.setTextColor(Color.WHITE);
        subtitle.setGravity(Gravity.CENTER);
        main.addView(subtitle);

        TextView welcome = new TextView(this);
        welcome.setText("\\nHello! I'm Yasmin. ❤️\\nHow can I help you today?");
        welcome.setTextSize(19);
        welcome.setTextColor(Color.WHITE);
        welcome.setPadding(10, 50, 10, 50);
        main.addView(welcome);

        EditText message = new EditText(this);
        message.setHint("Message Yasmin...");
        message.setHintTextColor(Color.LTGRAY);
        message.setTextColor(Color.WHITE);
        main.addView(message);

        Button send = new Button(this);
        send.setText("Send");
        main.addView(send);

        send.setOnClickListener(v -> {
            String text = message.getText().toString().trim();
            if (!text.isEmpty()) {
                welcome.setText("You: " + text
                    + "\\n\\nYasmin: Gemini AI connection will be added soon. ❤️");
                message.setText("");
            }
        });

        setContentView(main);
    }
}

