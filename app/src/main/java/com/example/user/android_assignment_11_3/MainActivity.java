package com.example.user.android_assignment_11_3;
//Package objects contain version information about the implementation and specification of a Java package.
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //public is a method and fields can be accessed by the members of any class.
    //class is a collections of objects.
    //created MainActivity and extends with AppCompatActivity which is Parent class.

    private DBhelper DbHelper;
    //Intiaizing the class.

    @Override
    //we use override to tells the compiler that the following method overrides a method of its superclass.
    protected void onCreate(Bundle savedInstanceState) {
        //protected can be accessed by within the package and class and subclasses
        //The Void class is an uninstantiable placeholder class to hold a reference to the Class object
        //representing the Java keyword void.
        //The savedInstanceState is a reference to a Bundle object that is passed into the onCreate method of every Android Activity
        // Activities have the ability, under special circumstances, to restore themselves to a previous state using the data stored in this bundle.
        super.onCreate(savedInstanceState);
        //Android class works in same.You are extending the Activity class which have onCreate(Bundle bundle) method in which meaningful code is written
        //So to execute that code in our defined activity. You have to use super.onCreate(bundle)
        setContentView(R.layout.activity_main);
        //R means Resource
        //layout means design
        //main is the xml you have created under res->layout->main.xml
        //Whenever you want to change your current Look of an Activity or when you move from one Activity to another .
        //he other Activity must have a design to show . So we call this method in onCreate and this is the second statement to set
        //the design

        DbHelper = new DBhelper(this);
        //performing insert operation and closing database
        Employee employee_One = new Employee(BitmapFactory.decodeResource(
                getResources(), R.drawable.photo),"Harry", 24);
        //Creates Bitmap objects from various sources, including files, streams, and byte-arrays.
        //decodeResource:Parameters
        // res	Resources: The resources object containing the image data
        //id	int: The resource id of the image data
        //  Returns
        // Bitmap	The decoded bitmap, or null if the image could not be decoded.
        DbHelper.open();
        //opens the database
        DbHelper.insertEmpDetails(employee_One);
        //insert the employee details
        DbHelper.close();
        //closes the database
        employee_One = null;
        //opens we close the database we will clear the database and opens the db empty next time
        DbHelper.open();
        //again opens the db
        employee_One = DbHelper.retriveEmpDetails();
        //retriving the emp details into object
        DbHelper.close();
        //closes the db
        //Finding texviews and imageview in java and using set method
        TextView empname = (TextView) findViewById(R.id.name);
        //A user interface element that displays text to the user.
        //findViewById:A user interface element that displays text to the user.
        empname.setText(employee_One.getName());
        //Sets the text to be displayed using a string resource identifier.
        //get the value of emp name from object
        ImageView empphoto = (ImageView) findViewById(R.id.photo);
        //Displays image resources, for example Bitmap or Drawable resources. ImageView is also commonly used to apply tints to an image and handle image scaling.
        //a bitmap is a mapping from some domain to bits. It is also called a bit array or bitmap index
        empphoto.setImageBitmap(employee_One.getBitmap());
        TextView empage = (TextView) findViewById(R.id.age);
        empage.setText("" + employee_One.getAge());
        //get the age from the object

    }
}
