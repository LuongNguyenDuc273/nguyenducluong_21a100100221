package deso1.nguyenducluong.dlu_21a100100221;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Detail extends AppCompatActivity {

    EditText foodName, foodPrice;
    ImageView foodImage;
    Button editButton;
    String key = "";
    String imgURL = "";
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        foodName = findViewById(R.id.detailFoodName);
        foodPrice = findViewById(R.id.detailFoodPrice);
        foodImage = findViewById(R.id.detailImage);
        editButton = findViewById(R.id.editButton);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            key = bundle.getString("Key");
            imgURL = bundle.getString("Image");
            foodName.setText(bundle.getString("Name"));
            foodPrice.setText(bundle.getString("Price"));
            Glide.with(this).load(bundle.getString("Image")).into(foodImage);
        }

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = foodName.getText().toString();
                String price = foodPrice.getText().toString();
                databaseReference= FirebaseDatabase.getInstance().getReference("Food").child(key);
                FoodModle foodData = new FoodModle(key, imgURL, foodName.getText().toString(), foodPrice.getText().toString());
                databaseReference.setValue(foodData).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(Detail.this, "Updated", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
                Intent intent = new Intent(Detail.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}