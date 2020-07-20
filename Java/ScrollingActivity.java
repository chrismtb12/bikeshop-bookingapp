package com.example.thefrothybikecobookingsys;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.Html;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;


public class ScrollingActivity extends AppCompatActivity {


    // Declaring String variable ( In which we are storing firebase server URL ).
    public static final String Firebase_Server_URL = "https://thefrothybikecobookingsys.firebaseio.com/";
    // Root Database Name for Firebase Database.
    public static final String Database_Path = "Customer Details";
    static Firebase firebase;
    static DatabaseReference databaseReference;

    TextView Bike_Arrive_DateTimeTextView;
    Spinner In_StockEditText, Needs_OrderedEditText;
    EditText CustomerNameEditText, Contact_NumberEditText, Bike_CollectionEditText, Part_QuotationEditText, Labour_QuotationEditText,
            Repair_QuotationEditText, Addit_InfoEditText, Bike_TypeEditText;

    // Declaring String variables to store name & phone number get from EditText.
    String CustomerNameHolder, Contact_NumberHolder, Bike_Arrive_DateTimeHolder, Bike_CollectionHolder, Part_QuotationHolder, Labour_QuotationHolder,
            Repair_QuotationHolder, In_StockHolder, Needs_OrderedHolder, Addit_InfoHolder, DateTimeHolder, Bike_TypeHolder;

    private CustomerName mCustomerName;
    private Button button_next;
    private Button button_submit;
    private ImageView imageViewWebsites;



    private TextWatcher fieldTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String Customer_NameInput = CustomerNameEditText.getText().toString().trim();
            String Contact_NumberInput = Contact_NumberEditText.getText().toString().trim();
            String Bike_CollectionInput = Bike_CollectionEditText.getText().toString().trim();
            String Part_QuotationInput = Part_QuotationEditText.getText().toString().trim();
            String Labour_QuotationInput = Labour_QuotationEditText.getText().toString().trim();
            String Repair_QuotationInput = Repair_QuotationEditText.getText().toString().trim();
            String In_StockInput = In_StockEditText.getSelectedItem().toString().trim();
            String Needs_OrderedInput = Needs_OrderedEditText.getSelectedItem().toString().trim();
            String Addit_InfoInput = Addit_InfoEditText.getText().toString().trim();
            String Bike_TypeInput = Bike_TypeEditText.getText().toString().trim();

            button_submit.setEnabled(!Customer_NameInput.isEmpty()
                    && !Contact_NumberInput.isEmpty()
                    && !Bike_CollectionInput.isEmpty() && !Part_QuotationInput.isEmpty()
                    && !Labour_QuotationInput.isEmpty() && !Repair_QuotationInput.isEmpty()
                    && !In_StockInput.isEmpty() && !Needs_OrderedInput.isEmpty()
                    && !Addit_InfoInput.isEmpty() && !Bike_TypeInput.isEmpty());
        }


        @Override
        public void afterTextChanged(Editable s) {

        }


    };
    private ConstraintLayout mParentLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);

        button_submit = (Button) findViewById(R.id.button_Submit);
        button_next = (Button) findViewById(R.id.button_searchcust);
        imageViewWebsites = (ImageView) findViewById(R.id.imageViewWebsites);




        bindViews();

        final Calendar BikeCollectionCalender = Calendar.getInstance();


        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                BikeCollectionCalender.set(Calendar.YEAR, year);
                BikeCollectionCalender.set(Calendar.MONTH, monthOfYear);
                BikeCollectionCalender.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

            private void updateLabel() {
                String myFormat = "dd/MM/yyyy";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.UK);

                Bike_CollectionEditText.setText(sdf.format(BikeCollectionCalender.getTime()));
            }
        };

        Bike_CollectionEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(ScrollingActivity.this, date, BikeCollectionCalender
                        .get(Calendar.YEAR), BikeCollectionCalender.get(Calendar.MONTH), BikeCollectionCalender
                        .get(Calendar.DAY_OF_MONTH)).show();
            }


        });


        button_next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                Animation animation= AnimationUtils.loadAnimation(ScrollingActivity.this,R.anim.bounce);

                button_next.startAnimation(animation);
                Intent next = new Intent(ScrollingActivity.this, SearchDatabase.class);
                startActivity(next);
            }
        });

        imageViewWebsites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent web = new Intent(ScrollingActivity.this, webSites.class);
                startActivity(web);
            }
        });


        Firebase.setAndroidContext(ScrollingActivity.this);

        firebase = new Firebase(Firebase_Server_URL);

        databaseReference = FirebaseDatabase.getInstance().getReference(Database_Path);


        CustomerNameEditText.addTextChangedListener(fieldTextWatcher);
        Contact_NumberEditText.addTextChangedListener(fieldTextWatcher);
        Bike_CollectionEditText.addTextChangedListener(fieldTextWatcher);
        Part_QuotationEditText.addTextChangedListener(fieldTextWatcher);
        Labour_QuotationEditText.addTextChangedListener(fieldTextWatcher);
        Repair_QuotationEditText.addTextChangedListener(fieldTextWatcher);

        Addit_InfoEditText.addTextChangedListener(fieldTextWatcher);
        Bike_TypeEditText.addTextChangedListener(fieldTextWatcher);


        button_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Animation animation= AnimationUtils.loadAnimation(ScrollingActivity.this,R.anim.sample_anim);

                button_submit.startAnimation(animation);

                if (mCustomerName == null) {
                    mCustomerName = new CustomerName();
                    saveNewCustomer();
                }
                resetViews();
                mCustomerName = null;


            }


        });

    }


    private void updateExistingCustomer() {
        GetDataFromEditText();
        setCustomerData();
        databaseReference.child(mCustomerName.getID()).setValue(mCustomerName);
        FancyToast.makeText(ScrollingActivity.this, "Customer was updated successfully!",
                FancyToast.LENGTH_LONG, FancyToast.INFO, false).show();
    }

    private void disableUnneededViews() {
        for (int i = 0; i < mParentLayout.getChildCount(); i++) {
            View currentView = mParentLayout.getChildAt(i);
            if (currentView instanceof EditText) {
                currentView.setEnabled(shouldBeEnabled(currentView.getId()));
            }
        }
    }


    private boolean shouldBeEnabled(int id) {
        //TODO: update this list with any view ID you want to be enabled while editing a customer
        Set<Integer> allowList = new HashSet<>();
        allowList.add(R.id.F_Part_Quotation);
        allowList.add(R.id.G_Labour_Quotation);
        allowList.add(R.id.H_Repair_Quotation);
        allowList.add(R.id.L_Addit_Info);
        allowList.add(R.id.J_In_Stock);
        allowList.add(R.id.K_Needs_Ordered);
        //if the passed ID belongs to the allowList then its view will be enabled
        return allowList.contains(id);
    }

    private void bindViews() {
        mParentLayout = (ConstraintLayout) findViewById(R.id.scrollingConstraintLayout);

        Bike_Arrive_DateTimeTextView = (TextView) findViewById(R.id.D_Bike_Arrive_DateTime);
        Bike_CollectionEditText = (EditText) findViewById(R.id.E_Bike_Collection);

        CustomerNameEditText = (EditText) findViewById(R.id.A_Customer_Name);
        Contact_NumberEditText = (EditText) findViewById(R.id.C_Contact_Number);
        Part_QuotationEditText = (EditText) findViewById(R.id.F_Part_Quotation);
        Labour_QuotationEditText = (EditText) findViewById(R.id.G_Labour_Quotation);
        Repair_QuotationEditText = (EditText) findViewById(R.id.H_Repair_Quotation);

        In_StockEditText = (Spinner) findViewById(R.id.J_In_Stock);
        Needs_OrderedEditText = (Spinner) findViewById(R.id.K_Needs_Ordered);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinner_array, android.R.layout.simple_spinner_item);
        {

        }
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        In_StockEditText.setAdapter(adapter);
        Needs_OrderedEditText.setAdapter(adapter);


        Addit_InfoEditText = (EditText) findViewById(R.id.L_Addit_Info);
        Bike_TypeEditText = (EditText) findViewById(R.id.Bike_Type);


    }

    private void resetViews() {
        for (int i = 0; i < mParentLayout.getChildCount(); i++) {
            if (mParentLayout.getChildAt(i) instanceof EditText) {
                EditText editText = (EditText) mParentLayout.getChildAt(i);
                editText.setEnabled(true);
                editText.setText("");
            }
        }
        Bike_Arrive_DateTimeTextView.setText("");
        Bike_TypeEditText.requestFocus();
    }

    private void initializeViewsContent() {
        CustomerNameEditText.setText(mCustomerName.getB_Customer_Name());
        Contact_NumberEditText.setText(mCustomerName.getC_Contact_Number());
        Bike_CollectionEditText.setText(mCustomerName.getE_Bike_Collection());
        Part_QuotationEditText.setText(mCustomerName.getF_Part_Quotation());
        Labour_QuotationEditText.setText(mCustomerName.getG_Labour_Quotation());
        Repair_QuotationEditText.setText(mCustomerName.getH_Repair_Quotation());

        Addit_InfoEditText.setText(mCustomerName.getL_Addit_Info());
        Bike_TypeEditText.setText(mCustomerName.getBike_Type());

        Bike_Arrive_DateTimeTextView.setText(mCustomerName.getD_Bike_Arrival());
    }

    private void saveNewCustomer() {

        GetDataFromEditText();
        GetDataFromTextView();
        setCustomerData();
        setArrivalTime();
        databaseReference.push().setValue(mCustomerName);

        // Showing Toast message after successfully data submit.
        FancyToast.makeText(ScrollingActivity.this, "Customer was created successfully!",
                FancyToast.LENGTH_LONG, FancyToast.SUCCESS, false).show();
    }

    private void setCustomerData() {
        mCustomerName.setA_DateTime(DateTimeHolder);

        // Adding first name into class function object.
        mCustomerName.setB_Customer_Name(CustomerNameHolder);

        // Adding contact number into class function object.
        mCustomerName.setC_Contact_Number(Contact_NumberHolder);


        // Adding bike collection into class function object.
        mCustomerName.setE_Bike_Collection(Bike_CollectionHolder);

        // Adding part quotation into class function object.
        mCustomerName.setF_Part_Quotation(Part_QuotationHolder);

        // Adding labour quotation into class function object.
        mCustomerName.setG_Labour_Quotation(Labour_QuotationHolder);

        // Adding repair quotation into class function object.
        mCustomerName.setH_Repair_Quotation(Repair_QuotationHolder);

        //Adding in stock into class function object.
        mCustomerName.setJ_In_Stock(In_StockHolder);

        //Adding needs ordered into class function object.
        mCustomerName.setK_Needs_Ordered(Needs_OrderedHolder);

        //Adding additional info into class function object.
        mCustomerName.setL_Addit_Info(Addit_InfoHolder);

        //Adding Bike Type into class function objects.
        mCustomerName.setBike_Type(Bike_TypeHolder);

    }

    private void setArrivalTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy 'at' HH:mm:ss");
        String currentDateandTime = sdf.format(new Date());

        // Adding bike arrival into class function object.
        mCustomerName.setD_Bike_Arrival(currentDateandTime);
    }

    public void GetDataFromEditText() {

        CustomerNameHolder = CustomerNameEditText.getText().toString().trim();
        Contact_NumberHolder = Contact_NumberEditText.getText().toString().trim();
        Bike_CollectionHolder = Bike_CollectionEditText.getText().toString().trim();
        Part_QuotationHolder = Part_QuotationEditText.getText().toString().trim();
        Labour_QuotationHolder = Labour_QuotationEditText.getText().toString().trim();
        Repair_QuotationHolder = Repair_QuotationEditText.getText().toString().trim();
        In_StockHolder = In_StockEditText.getSelectedItem().toString().trim();
        Needs_OrderedHolder = Needs_OrderedEditText.getSelectedItem().toString().trim();
        Addit_InfoHolder = Addit_InfoEditText.getText().toString().trim();
        Bike_TypeHolder = Bike_TypeEditText.getText().toString().trim();
    }

    public void GetDataFromTextView() {

        Bike_Arrive_DateTimeHolder = Bike_Arrive_DateTimeTextView.getText().toString().trim();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
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