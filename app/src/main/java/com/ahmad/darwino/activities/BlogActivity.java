package com.ahmad.darwino.activities;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import com.ahmad.darwino.R;


public class BlogActivity extends BaseActivity {

    ImageView write_command, share_command, show_command, backPage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog);

        init();


        backPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        write_command.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogWriteCommand();
            }
        });

    }

    void init(){
        write_command = findViewById(R.id.blog_write_comment);
        share_command = findViewById(R.id.blog_share);
        show_command = findViewById(R.id.blog_show_comment);
        backPage = findViewById(R.id.blog_back);
    }


    void DialogWriteCommand() {

        final Dialog dialog = new Dialog(BlogActivity.this, R.style.NewDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_write_command);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();

        ImageView close = dialog.findViewById(R.id.dialog_command_close);
        Button ok = dialog.findViewById(R.id.dialog_command_ok);
        EditText command = dialog.findViewById(R.id.dialog_command_text);





        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }


    @Override
    public void loggedIn() {

    }

    @Override
    public void loginFailed(String message) {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
