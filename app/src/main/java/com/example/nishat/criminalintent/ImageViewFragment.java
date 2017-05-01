package com.example.nishat.criminalintent;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import java.io.File;

/**
 * Created by Nishat on 1/10/2017.
 */

public class ImageViewFragment extends DialogFragment {
    private static final String ARG_IMG_FILE = "image_location";
    private ImageView mImageView;

    public static ImageViewFragment newInstance(File imageFile) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_IMG_FILE, imageFile);
        ImageViewFragment fragment = new ImageViewFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        File mPhotoFile = (File) getArguments().getSerializable(ARG_IMG_FILE);
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_image, null);
        mImageView = (ImageView) v.findViewById(R.id.dialog_image_image_view);

        if (mPhotoFile == null || !mPhotoFile.exists()) {
            mImageView.setImageDrawable(null);
        } else {
            Bitmap bitmap = PictureUtils.getScaledBitmap(
                    mPhotoFile.getPath(), getActivity());
            mImageView.setImageBitmap(bitmap);
        }

        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .create();
    }
}
