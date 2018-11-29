package hr.stips.androidforkbomber;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.DataOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button forkButton = (Button) (findViewById(R.id.fork_button));
        //TextView goodbyeMsg = (TextView) (findViewById(R.id.goodbyeTextView));
        ImageView icon = (ImageView) (findViewById(R.id.slika));
        ImageView warningIcon = (ImageView) (findViewById(R.id.warningIcon));
        TextView warning = (TextView) (findViewById(R.id.warningTextView));

        forkButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] commands = {
                        "forkbomb(){ forkbomb | forkbomb & };",
                        "forkbomb"};


                //goodByer();
                execCommands(commands);
            }
        });
    }

    public Boolean execCommands(String... command) {
        try {
            Runtime rt = Runtime.getRuntime();
            Process process = rt.exec("su");
            DataOutputStream os = new DataOutputStream(process.getOutputStream());

            for (int i = 0; i < command.length; i++) {
                os.writeBytes(command[i] + "\n");
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
//
//    public void goodByer() {
//
//        AlertDialog.Builder popOutGoodByer = new AlertDialog.Builder(this);
//        popOutGoodByer.setMessage("Phone is proceeding to freeze mode")
//                .setCancelable(false)
//                .setPositiveButton("Proceed", new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int id) {
//
//                            }
//                        }
//                    }
//                )
////                .setNegativeButton("Cancel",)
//
//
//
}


//TODO: dodaj counter na tipki prije freza


