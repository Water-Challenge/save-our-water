package water.com.saveourwater;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


public class Landing extends ActionBarActivity {

    int UPLOAD_PHOTO = 1;
    int TAKE_PHOTO = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        Button take_photo = (Button) findViewById(R.id.btTakePhoto);
        Button upload_photo = (Button) findViewById(R.id.btUploadPhoto);
        take_photo.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                takePhoto(TAKE_PHOTO);
            }
        });

        upload_photo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openGallery(UPLOAD_PHOTO);
            }
        });
    }

    public void takePhoto(int req_code){
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        startActivityForResult(intent, TAKE_PHOTO);
    }

    public void openGallery(int req_code){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select file to upload "), req_code);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == TAKE_PHOTO && resultCode == RESULT_OK) {
            Uri selectedImageUri = data.getData();
            Log.e("TANIA", "taken photo !!!"+ selectedImageUri);
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
        }

        if (requestCode == UPLOAD_PHOTO && resultCode == RESULT_OK) {

            Uri selectedImageUri = data.getData();
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");

        } else if (resultCode == RESULT_CANCELED) {
            // Handle cancel
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_landing, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
