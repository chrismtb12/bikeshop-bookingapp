package com.example.thefrothybikecobookingsys;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateDialogFragment extends DialogFragment {

    UpdateDialogListener updateDialogListener;
    private EditText mPartQuote;
    private EditText mLabourQuote;
    private EditText mRepairQuote;
    private EditText mPartToOrder;
    private EditText mPartInStock;
    private EditText mDoPartsNeedOrdered;
    private TextView mCustomerNameHeader;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            updateDialogListener = (UpdateDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    " must implement UpdateDialogListener!");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.update_dialog, null);
        dialogBuilder.setView(view);
        bindViews(view);
        initializeViewsContent();

        DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:

                        getArguments().putString("part quote", mPartQuote.getText().toString());
                        getArguments().putString("labour quote", mLabourQuote.getText().toString());
                        getArguments().putString("repair quote", mRepairQuote.getText().toString());
                        getArguments().putString("parts to order", mPartToOrder.getText().toString());
                        getArguments().putString("in stock", mPartInStock.getText().toString());
                        getArguments().putString("do parts need ordered", mDoPartsNeedOrdered.getText().toString());

                        updateDialogListener.onDialogPositiveClick(UpdateDialogFragment.this);
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        updateDialogListener.onDialogNegativeClick(UpdateDialogFragment.this);
                        break;
                    default:
                        Toast.makeText(dialogBuilder.getContext(), "Something went wrong...", Toast.LENGTH_LONG).show();
                }
            }
        };
        dialogBuilder.setPositiveButton("Update", onClickListener);
        dialogBuilder.setNegativeButton("Cancel", onClickListener);
        return dialogBuilder.create();
    }

    private void initializeViewsContent() {
        mCustomerNameHeader.setText(getArguments().getString("name"));
        mPartQuote.setText(getArguments().getString("part quote"));
        mLabourQuote.setText(getArguments().getString("labour quote"));
        mRepairQuote.setText(getArguments().getString("repair quote"));
        mPartToOrder.setText(getArguments().getString("parts to order"));
        mPartInStock.setText(getArguments().getString("in stock"));
        mDoPartsNeedOrdered.setText(getArguments().getString("do parts need ordered"));
    }

    private void bindViews(View view) {
        mPartQuote = view.findViewById(R.id.editTextNewPartQ);
        mLabourQuote = view.findViewById(R.id.editTextNewLabourQ);
        mRepairQuote = view.findViewById(R.id.editTextNewRepairQ);
        mPartToOrder = view.findViewById(R.id.editTextNewPartToBeOrdered);
        mPartInStock = view.findViewById(R.id.editTextNewPartInStockOption);
        mDoPartsNeedOrdered = view.findViewById(R.id.editTextNewPartToBeOrderedOption);
        mCustomerNameHeader = view.findViewById(R.id.customerNameHeader);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.update_dialog, container, false);

        // Set transparent background and no title
        if (getDialog() != null && getDialog().getWindow() != null) {
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        }
        return view;
    }

    public interface UpdateDialogListener {
        public void onDialogPositiveClick(DialogFragment dialog);

        public void onDialogNegativeClick(DialogFragment dialog);
    }

}
