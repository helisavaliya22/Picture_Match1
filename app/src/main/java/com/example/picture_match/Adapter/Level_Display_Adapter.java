package com.example.picture_match.Adapter;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.picture_match.Activity.Level_Display_Activity;
import com.example.picture_match.Activity.Level_ListView_Activity;
import com.example.picture_match.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Level_Display_Adapter extends BaseAdapter {
    Level_Display_Activity level_display_activity;
    List<String> arrayList;
    String level;
    TextView timeshow;
    ProgressBar progressBar;
    int click=1,pos1,pos2,win=0,time,interval;
    View mask2;
    Runnable runnable;
    CountDownTimer timer;
    private long delayTime=40000;
    private int maxTime=40;

    public Level_Display_Adapter(Level_Display_Activity level_display_activity, List<String> arrayList, TextView timeshow, ProgressBar progressBar, String level) {
        this.level_display_activity = level_display_activity;
        this.arrayList = arrayList;
        this.timeshow = timeshow;
        this.progressBar = progressBar;
        this.level = level;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        view = LayoutInflater.from(level_display_activity).inflate(R.layout.level_gridview_item,parent,false);
        ImageView imageView = view.findViewById(R.id.img_disp);
        View mask1 = view.findViewById(R.id.mask1);
        RelativeLayout relativeLayout = view.findViewById(R.id.relative);

        InputStream inputStream = null;
        try {
            inputStream = level_display_activity.getAssets().open("Images/" + arrayList.get(position));
            Drawable drawable = Drawable.createFromStream(inputStream, null);
            imageView.setImageDrawable(drawable);
            inputStream.close();
            Log.d("MMM", "getView: Position="+position);

        } catch (IOException e) {
            new RuntimeException(e);
        }
        startCountDown(mask1,relativeLayout,position);
        return view;
    }
    private void startCountDown(View mask1, RelativeLayout relativeLayout, int position) {

        new CountDownTimer(6000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                progressBar.setMax(5);
                time = (int) (millisUntilFinished/1000);
                timeshow.setText(""+time+"/0");
                progressBar.setProgress(time);
            }
            @Override
            public void onFinish() {
                playGame(mask1,relativeLayout,position);
                if (level.equals("no_time_limit"))
                {
                   timer= new CountDownTimer(15000, 1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            progressBar.setMax(15);
                            interval = (int) (millisUntilFinished/1000);
                            timeshow.setText(""+(progressBar.getMax()-interval)+"/0");
                            progressBar.setProgress(progressBar.getMax()-interval);
                            if(interval==delayTime)
                            {
                                cancel();
                            }

                        }
                        @Override
                        public void onFinish() {

                        }
                    }.start();
                }
                if (level.equals("normal"))
                {
                    new CountDownTimer(30000, 1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            progressBar.setMax(30);
                            int time = (int) (millisUntilFinished/1000);
                            timeshow.setText(""+(progressBar.getMax()-time)+"/30");
                            progressBar.setProgress(progressBar.getMax()-time);
                        }
                        @Override
                        public void onFinish() {
                            MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(level_display_activity);
                            builder.setTitle("TRY AGAIN");
                            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent=new Intent(level_display_activity, Level_Display_Activity.class);
                                    level_display_activity.startActivity(intent);
                                }
                            });
                            builder.show();
                            builder.setNegativeButton("CANCLE", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(level_display_activity, Level_ListView_Activity.class);
                                    level_display_activity.startActivity(intent);
                                }
                            });
                            builder.show();
                            relativeLayout.setEnabled(false);
                        }
                    }.start();
                }
                if (level.equals("hard"))
                {
                    new CountDownTimer(20000, 1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            progressBar.setMax(20);
                            int time = (int) (millisUntilFinished/1000);
                            timeshow.setText(""+(progressBar.getMax()-time)+"/20");
                            progressBar.setProgress(progressBar.getMax()-time);
                        }
                        @Override
                        public void onFinish() {
                            MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(level_display_activity);
                            builder.setTitle("TRY AGAIN");
                            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent=new Intent(level_display_activity, Level_Display_Activity.class);
                                    level_display_activity.startActivity(intent);
                                }
                            });
                            builder.show();
                            builder.setNegativeButton("CANCLE", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(level_display_activity, Level_ListView_Activity.class);
                                    level_display_activity.startActivity(intent);
                                }
                            });
                            builder.show();
                            relativeLayout.setEnabled(false);
                        }
                    }.start();
                }
            }
        }.start();
    }
    private void playGame(View mask1, RelativeLayout relativeLayout, int position)
    {
        mask1.setVisibility(View.VISIBLE);
        Handler handler = new Handler();
        if (click == 1)
        {
            relativeLayout.setOnClickListener(v -> {
                if (click == 1)
                {
                    mask1.setVisibility(View.INVISIBLE);
                    pos1 = position;
                    mask2 = mask1;
                    click = 3;
                    runnable = new Runnable() {
                        @Override
                        public void run() {
                            click = 2;
                        }
                    };
                    handler.postDelayed(runnable,300);
                }
                if (click == 2)
                {
                    mask1.setVisibility(View.INVISIBLE);
                    pos2 = position;
                    click = 3;
                    if (arrayList.get(pos1).equals(arrayList.get(pos2)))
                    {
                        win++;
                        if (win==(arrayList.size()/2))
                        {
                            Toast.makeText(level_display_activity, "WIN", Toast.LENGTH_LONG).show();
                            Log.d("TTT", "playGame: time"+(progressBar.getMax()-interval));
                            //progressBar.setMax(progressBar.getMax()-interval);
                            delayTime=(progressBar.getMax()-interval)*1000;
                            //maxTime=progressBar.getMax()-interval*1000;

                        }
                        runnable = new Runnable() {
                            @Override
                            public void run() {
                                click = 1;
                            }
                        };
                        handler.postDelayed(runnable,500);
                    }
                    else {
                        runnable = new Runnable() {
                            @Override
                            public void run() {
                                mask1.setVisibility(View.VISIBLE);
                                mask2.setVisibility(View.VISIBLE);
                                click = 1;
                            }
                        };
                        handler.postDelayed(runnable,500);
                    }
                }
            });
        }
    }
}
