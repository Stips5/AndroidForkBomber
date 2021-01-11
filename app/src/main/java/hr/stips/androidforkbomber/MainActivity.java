package hr.stips.androidforkbomber;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.DataOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    Button forkButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        forkButton = findViewById(R.id.fork_button);
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (RootUtil.isDeviceRooted()) {
            forkButton.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String[] commands = {
                            "forkbomb(){ forkbomb | forkbomb & };",
                            "forkbomb"};
                    execCommands(commands);
                }
            });
        } else {
            Toast.makeText(this, "Phone not rooted", Toast.LENGTH_LONG).show();
            forkButton.setEnabled(false);
        }
    }

    public Boolean execCommands(String... command) {
        try {
            Runtime rt = Runtime.getRuntime();
            Process process = rt.exec("su");
            DataOutputStream os = new DataOutputStream(process.getOutputStream());

            for (String s : command) {
                os.writeBytes(s + "\n");
                os.flush();
            }
            os.writeBytes("exit\n");
            os.flush();
            process.waitFor();
        } catch (IOException e) {
            return false;
        } catch (InterruptedException e) {
            return false;
        }
        return true;

    }
}