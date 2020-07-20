package com.example.thefrothybikecobookingsys;

import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageButton;

import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.shashank.sony.fancytoastlib.FancyToast;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.ArrayList;

import static com.example.thefrothybikecobookingsys.ScrollingActivity.databaseReference;

public class SearchDatabase extends AppCompatActivity implements UpdateDialogFragment.UpdateDialogListener {

    private EditText mSearchField;
    private ImageButton mSearchBtn;

    private RecyclerView mResultList;
    private DatabaseReference mCustomerNameDatabase;
    private CustomerName mChosenCustomer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchdatabase);

        mCustomerNameDatabase = FirebaseDatabase.getInstance().getReference("Customer Details");

        mSearchField = (EditText) findViewById(R.id.search_field);
        mSearchBtn = (ImageButton) findViewById(R.id.searchbtn);

        mResultList = (RecyclerView) findViewById(R.id.result_list);
        mResultList.setHasFixedSize(true);
        mResultList.setLayoutManager(new LinearLayoutManager(this));

        mSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Animation animation= AnimationUtils.loadAnimation(SearchDatabase.this,R.anim.blink_anim);

                mSearchBtn.startAnimation(animation);

                String searchText = mSearchField.getText().toString();
                String searchTextName = mSearchField.getText().toString();

                firebaseUserSearch(searchText);
                firebaseUserSearchName(searchTextName);
            }
        });


    }

    private void firebaseUserSearch(String searchText) {

        FancyToast.makeText(SearchDatabase.this, "Searching Database...",
                FancyToast.LENGTH_SHORT, FancyToast.INFO, false).show();

        Query firebaseSearchQuery = mCustomerNameDatabase.orderByChild("bike_Type").startAt(searchText).endAt(searchText + "\uf8ff");


        FirebaseRecyclerAdapter<CustomerName, CustomerNameViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<CustomerName, CustomerNameViewHolder>(

                        CustomerName.class,
                        R.layout.list_layout,
                        CustomerNameViewHolder.class,
                        firebaseSearchQuery


                ) {
                    @Override
                    protected CustomerName parseSnapshot(DataSnapshot snapshot) {
                        CustomerName bike = snapshot.getValue(CustomerName.class);
                        bike.setID(snapshot.getKey());
                        return bike;
                    }

                    @Override
                    protected void populateViewHolder(final CustomerNameViewHolder viewHolder, final CustomerName model, int position) {

                        viewHolder.mView.setOnLongClickListener(new View.OnLongClickListener() {
                            @Override
                            public boolean onLongClick(View v) {
                                mChosenCustomer = model;
                                displayUpdateDialog(mChosenCustomer);
                                return true;
                            }
                        });
                        viewHolder.setDetails(model.getB_Customer_Name(),
                                model.getC_Contact_Number(), model.getD_Bike_Arrival(), model.getE_Bike_Collection(),
                                model.getF_Part_Quotation(), model.getG_Labour_Quotation(), model.getH_Repair_Quotation(),
                                String.valueOf(model.getFinalPrice()), model.getJ_In_Stock(), model.getK_Needs_Ordered(), model.getL_Addit_Info(), model.getBike_Type());

                    }
                };

        mResultList.setAdapter(firebaseRecyclerAdapter);


    }

    private void firebaseUserSearchName(String searchTextName) {

        FancyToast.makeText(SearchDatabase.this, "Searching Database...",
                FancyToast.LENGTH_SHORT, FancyToast.INFO, false).show();

        Query firebaseSearchQueryName = mCustomerNameDatabase.orderByChild("bike_Type").startAt(searchTextName).endAt(searchTextName + "\uf8ff");


        FirebaseRecyclerAdapter<CustomerName, CustomerNameViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<CustomerName, CustomerNameViewHolder>(

                        CustomerName.class,
                        R.layout.list_layout,
                        CustomerNameViewHolder.class,
                        firebaseSearchQueryName


                ) {
                    @Override
                    protected CustomerName parseSnapshot(DataSnapshot snapshot) {
                        CustomerName bike = snapshot.getValue(CustomerName.class);
                        bike.setID(snapshot.getKey());
                        return bike;
                    }

                    @Override
                    protected void populateViewHolder(final CustomerNameViewHolder viewHolder, final CustomerName model, int position) {

                        viewHolder.mView.setOnLongClickListener(new View.OnLongClickListener() {
                            @Override
                            public boolean onLongClick(View v) {
                                mChosenCustomer = model;
                                displayUpdateDialog(mChosenCustomer);
                                return true;
                            }
                        });
                        viewHolder.setDetails(model.getB_Customer_Name(),
                                model.getC_Contact_Number(), model.getD_Bike_Arrival(), model.getE_Bike_Collection(),
                                model.getF_Part_Quotation(), model.getG_Labour_Quotation(), model.getH_Repair_Quotation(),
                                String.valueOf(model.getFinalPrice()), model.getJ_In_Stock(), model.getK_Needs_Ordered(), model.getL_Addit_Info(), model.getBike_Type());

                    }
                };

        mResultList.setAdapter(firebaseRecyclerAdapter);


    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        mChosenCustomer.setF_Part_Quotation(dialog.getArguments().getString("part quote"));
        mChosenCustomer.setG_Labour_Quotation(dialog.getArguments().getString("labour quote"));
        mChosenCustomer.setH_Repair_Quotation(dialog.getArguments().getString("repair quote"));
        mChosenCustomer.setL_Addit_Info(dialog.getArguments().getString("parts to order"));
        mChosenCustomer.setK_Needs_Ordered(dialog.getArguments().getString("do parts need ordered"));
        mChosenCustomer.setJ_In_Stock(dialog.getArguments().getString("in stock"));
//      mChosenCustomer.setFinalPrice(
//                Double.valueOf(mChosenCustomer.getF_Part_Quotation()) +
//                Double.valueOf(mChosenCustomer.getG_Labour_Quotation()) +
//                Double.valueOf(mChosenCustomer.getH_Repair_Quotation())
//        );
        databaseReference.child(mChosenCustomer.getID()).setValue(mChosenCustomer);

        FancyToast.makeText(SearchDatabase.this, "Customer was updated successfully!",
                FancyToast.LENGTH_LONG, FancyToast.SUCCESS, false).show();
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        dialog.getDialog().cancel();
    }

    // View Holder Class

    private void displayUpdateDialog(CustomerName customer) {
        Bundle bundle = new Bundle();
        bundle.putString("name", customer.getB_Customer_Name());
        bundle.putString("part quote", customer.getF_Part_Quotation());
        bundle.putString("labour quote", customer.getG_Labour_Quotation());
        bundle.putString("repair quote", customer.getH_Repair_Quotation());
        bundle.putString("final price", String.valueOf(customer.getFinalPrice()));
        bundle.putString("parts to order", customer.getL_Addit_Info());
        bundle.putString("in stock", customer.getJ_In_Stock());
        bundle.putString("do parts need ordered", customer.getK_Needs_Ordered());
        DialogFragment dialogFragment = new UpdateDialogFragment();
        dialogFragment.setArguments(bundle);
        dialogFragment.show(getSupportFragmentManager(), "UpdateDialogFragment");
    }

    public static class CustomerNameViewHolder extends RecyclerView.ViewHolder {


        View mView;

        public CustomerNameViewHolder(View itemView) {
            super(itemView);

            mView = itemView;


        }

        public void setDetails(String userCustomerName, String userNumber, String userBikeArrival, String userBikeCollection,
                               String userpartQ, String userlabourQ, String userrepairQ, String userFinalPrice,
                               String userInStock, String userNeedOrdered, String userAdditionalInfo, String userBikeType) {

            TextView customername = mView.findViewById(R.id.textViewCustomerName);
            TextView contactnumber = mView.findViewById(R.id.textViewNumber);
            TextView bikearrival = mView.findViewById(R.id.textViewBikeArrival);
            TextView bikecollection = mView.findViewById(R.id.textViewBikeCollection);
            TextView partQ = mView.findViewById(R.id.textViewPartQ);
            TextView labourQ = mView.findViewById(R.id.textViewLabourQ);
            TextView repairQ = mView.findViewById(R.id.textViewRepairQ);
            TextView finalprice = mView.findViewById(R.id.textViewFinalPrice);
            TextView instock = mView.findViewById(R.id.textViewInStock);
            TextView needordered = mView.findViewById(R.id.textViewNeedOrdered);
            TextView additionalinformation = mView.findViewById(R.id.textViewAdditionalInfo);
            TextView biketype = mView.findViewById(R.id.textViewBikeType);


            customername.setText(userCustomerName);
            contactnumber.setText(userNumber);
            bikearrival.setText(userBikeArrival);
            bikecollection.setText(userBikeCollection);
            partQ.setText(userpartQ);
            labourQ.setText(userlabourQ);
            repairQ.setText(userrepairQ);
            finalprice.setText(new DecimalFormat("##.##").format(Double.valueOf(userFinalPrice)));
            instock.setText(userInStock);
            needordered.setText(userNeedOrdered);
            additionalinformation.setText(userAdditionalInfo);
            biketype.setText(userBikeType);


        }
    }
}

