package com.anam.ai;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.view.Gravity;
import android.view.inputmethod.EditorInfo;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Button;
import android.widget.ScrollView;
import android.content.res.ColorStateList;

public class MainActivity extends Activity {

    LinearLayout chatMessages;
    ScrollView chatScroll;
    EditText messageInput;

    int dp(int value) {
        return (int) (value * getResources().getDisplayMetrics().density);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setSoftInputMode(
            WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
        );

        LinearLayout main = new LinearLayout(this);
        main.setOrientation(LinearLayout.VERTICAL);
        main.setPadding(dp(16), dp(24), dp(16), dp(12));
        main.setBackgroundColor(Color.rgb(12, 10, 30));

        TextView title = new TextView(this);
        title.setText("ANAM AI");
        title.setTextSize(30);
        title.setTextColor(Color.rgb(190, 130, 255));
        title.setGravity(Gravity.CENTER);
        main.addView(title);

        TextView subtitle = new TextView(this);
        subtitle.setText("Yasmin - Your AI Companion ❤️");
        subtitle.setTextSize(15);
        subtitle.setTextColor(Color.WHITE);
        subtitle.setGravity(Gravity.CENTER);
        subtitle.setPadding(0, dp(5), 0, dp(15));
        main.addView(subtitle);

        chatScroll = new ScrollView(this);
        chatScroll.setFillViewport(true);

        chatMessages = new LinearLayout(this);
        chatMessages.setOrientation(LinearLayout.VERTICAL);
        chatMessages.setPadding(dp(4), dp(8), dp(4), dp(8));
        chatScroll.addView(chatMessages);

        LinearLayout.LayoutParams scrollParams =
            new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 0, 1
            );
        main.addView(chatScroll, scrollParams);

        addMessage("Yasmin",
            "Hello! Main Yasmin hoon. ❤️\nAap mujhse baat kar sakte hain.");

        LinearLayout composer = new LinearLayout(this);
        composer.setOrientation(LinearLayout.HORIZONTAL);
        composer.setGravity(Gravity.CENTER_VERTICAL);
        composer.setPadding(0, dp(10), 0, 0);

        messageInput = new EditText(this);
        messageInput.setHint("Message Yasmin...");
        messageInput.setHintTextColor(Color.LTGRAY);
        messageInput.setTextColor(Color.WHITE);
        messageInput.setTextSize(16);
        messageInput.setSingleLine(true);
        messageInput.setImeOptions(EditorInfo.IME_ACTION_SEND);
        messageInput.setPadding(dp(12), dp(10), dp(12), dp(10));
        messageInput.setBackgroundTintList(
            ColorStateList.valueOf(Color.rgb(190, 130, 255))
        );

        LinearLayout.LayoutParams inputParams =
            new LinearLayout.LayoutParams(0, dp(52), 1);
        composer.addView(messageInput, inputParams);

        Button send = new Button(this);
        send.setText("Send");
        send.setTextColor(Color.WHITE);
        send.setBackgroundTintList(
            ColorStateList.valueOf(Color.rgb(130, 75, 210))
        );
        composer.addView(send);

        main.addView(composer);

        send.setOnClickListener(v -> sendMessage());

        messageInput.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEND) {
                sendMessage();
                return true;
            }
            return false;
        });

        setContentView(main);
    }

    void sendMessage() {
        String text = messageInput.getText().toString().trim();

        if (text.isEmpty()) {
            return;
        }

        addMessage("You", text);
        messageInput.setText("");

        addMessage("Yasmin",
            "Maine aapka message dekh liya hai. ❤️\n" +
            "Gemini AI connection abhi jodna baaki hai.");
    }

    void addMessage(String sender, String text) {
        TextView bubble = new TextView(this);
        bubble.setText(sender + "\n" + text);
        bubble.setTextSize(16);
        bubble.setTextColor(Color.WHITE);
        bubble.setPadding(dp(14), dp(12), dp(14), dp(12));
        bubble.setBackgroundTintList(
            ColorStateList.valueOf(
                sender.equals("You")
                    ? Color.rgb(75, 55, 120)
                    : Color.rgb(45, 38, 70)
            )
        );
        bubble.setBackground(
            getDrawable(android.R.drawable.dialog_holo_light_frame)
        );
        bubble.setBackgroundTintList(
            ColorStateList.valueOf(
                sender.equals("You")
                    ? Color.rgb(75, 55, 120)
                    : Color.rgb(45, 38, 70)
            )
        );

        LinearLayout.LayoutParams params =
            new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            );

        params.setMargins(
            dp(4), dp(5), dp(4), dp(8)
        );

        params.gravity = sender.equals("You")
            ? Gravity.END : Gravity.START;

        bubble.setMaxWidth(
            (int) (getResources().getDisplayMetrics().widthPixels * 0.80)
        );

        chatMessages.addView(bubble, params);

        chatScroll.post(() ->
            chatScroll.fullScroll(ScrollView.FOCUS_DOWN)
        );
    }
}
