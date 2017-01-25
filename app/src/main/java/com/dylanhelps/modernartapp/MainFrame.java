package com.dylanhelps.modernartapp;


import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.SeekBar;

import java.util.Random;


/**
 * Created by HJ Chen on 1/24/17
 */
public class MainFrame extends Fragment {
    private final static Class aClass = MainFrame.class;
    private final static String APPNAME = aClass.getName();
    private RelativeLayout relativeLayout;

    public MainFrame() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mainFrame= inflater.inflate(R.layout.fragment_main_frame, container, false);
        relativeLayout = (RelativeLayout) mainFrame.findViewById(R.id.canvas);

        SeekBar seekBar=(SeekBar) mainFrame.findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener(){
                    int progressIncrement =0;
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                        progressIncrement = i;
                        for(int k=0;k<relativeLayout.getChildCount();k++){
                            View view = new View(getActivity().getApplicationContext());
                            int id = -1;
                            try {
                                view = relativeLayout.getChildAt(k);
                                id = view.getId();
                                Log.i(APPNAME,"Child view: "+id);
                            }
                            catch(Exception e){
                                Log.e(APPNAME,"Cannot get child view #"+k);
                            }

                            Integer originalColor;
                            try {
                                if(id == -1){
                                    Log.e(APPNAME,"Child view ID is -1");
                                }
                                originalColor = MainFrame.this.getColor(getActivity().getApplicationContext(),id);
                                // check if the rectangle is non-white/non-grey
                                if (originalColor != MainFrame.this.getColor(getActivity().getApplicationContext(),R.color.white) ||
                                        originalColor!=MainFrame.this.getColor(getActivity().getApplicationContext(),R.color.color_light_grey
                                        )) {
                                    boolean status = setBackgroundColorBasedOnProgress(view, originalColor);
                                    if (status == false) {
                                        Log.e(APPNAME, "Cannot change color");
                                    }
                                }
                            }
                            catch (Exception e){
                                Log.e(APPNAME,e.getMessage());
                            }// end catch

                        } // end for
                    } // end onProgressChanged


                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {}

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {}

                    private boolean setBackgroundColorBasedOnProgress(View box, int OriginalBoxColor) {
                        try {
                            box.setBackgroundColor(
                                    getRandomColor()
                            );
                            return true;
                        }
                        catch (Exception e){
                            return false;
                        }
                    }

                    private int getRandomColor(){
                        Random rnd = new Random();
                        int color = Color.argb(255, rnd.nextInt(156), rnd.nextInt(256), rnd.nextInt(256));
                        return color;
                    }
                }
        );
        return mainFrame;
    }


    private final int getColor(Context context, int id){
//        if(Build.VERSION.SDK_INT>=23) {
//            return ContextCompatApi23.getColor(context, id);
//        }
//        else
        if(Build.VERSION.SDK_INT<23 && Build.VERSION.SDK_INT>18){
            return ContextCompat.getColor(context,id);
        }
        else return context.getResources().getColor(id);
    }

}
