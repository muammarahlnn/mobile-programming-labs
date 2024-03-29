package com.muammarahlnn.simplefragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class OptionDialogFragment extends DialogFragment {

    private Button btnChoose, btnClose;
    private RadioGroup rgOptions;
    private RadioButton rbSaf, rbMou, rbLvg, rbMoyes;
    private OnOptionDialogListener onOptionDialogListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Fragment fragment = getParentFragment();
        if (fragment instanceof DetailCategoryFragment) {
            DetailCategoryFragment detailCategoryFragment = (DetailCategoryFragment) fragment;
            this.onOptionDialogListener = detailCategoryFragment.optionDialogListener;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_option_dialog, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnChoose = view.findViewById(R.id.btn_choose);
        btnClose = view.findViewById(R.id.btn_close);
        rgOptions = view.findViewById(R.id.rg_options);
        rbSaf = view.findViewById(R.id.rb_saf);
        rbLvg = view.findViewById(R.id.rb_lvg);
        rbMou = view.findViewById(R.id.rb_mou);
        rbMoyes = view.findViewById(R.id.rb_moyes);

        btnChoose.setOnClickListener(v -> {
            int checkedRadioButtonId = rgOptions.getCheckedRadioButtonId();
            if (checkedRadioButtonId != -1) {
                String coach = null;
                if (checkedRadioButtonId == R.id.rb_saf) {
                    coach = rbSaf.getText().toString().toString();
                } else if (checkedRadioButtonId == R.id.rb_lvg) {
                    coach = rbLvg.getText().toString().toString();
                } else if (checkedRadioButtonId == R.id.rb_mou) {
                    coach = rbMou.getText().toString().toString();
                } else if (checkedRadioButtonId == R.id.rb_moyes) {
                    coach = rbMoyes.getText().toString().toString();
                }

                if (onOptionDialogListener != null) {
                    onOptionDialogListener.onOptionChosen(coach);
                }
                getDialog().dismiss();
            }
        });
        btnClose.setOnClickListener(v -> getDialog().cancel());
    }

    @Override
    public void onDetach() {
        super.onDetach();
        onOptionDialogListener = null;
    }

    public interface OnOptionDialogListener {
        void onOptionChosen(String text);
    }
}