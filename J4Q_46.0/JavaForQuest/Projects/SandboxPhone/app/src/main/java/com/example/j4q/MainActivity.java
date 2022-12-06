package com.example.j4q;

import android.os.Bundle;
import edu.ufl.digitalworlds.j4q.activities.GyroscopicActivity;

public class MainActivity extends GyroscopicActivity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        //Enable the following line for AR-type experience (without position tracking; orientation only)
        //showCamera();
    }

    public void Start(){
        //Welcome to the J4Q (Java For Quest) Project!

        //This method will run once in the beginning of the program.
        //Write here code that loads your assets and composes your scene.
    }

    public  void Update(){
        //This method will run in every frame.
        //Write here code that modifies your variables and animates your scene.
    }

}
