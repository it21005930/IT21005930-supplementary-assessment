package IT21005930_supplementary_assessment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.IT21005930_supplementary_assessment.R;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList note_id, note, date, dis;

    CustomAdapter(Activity activity, Context context, ArrayList note_id, ArrayList note, ArrayList date,
                  ArrayList dis){
        this.activity = activity;
        this.context = context;
        this.note_id = note_id;
        this.note = note;
        this.date = date;
        this.dis = dis;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.note_id_txt.setText(String.valueOf(note_id.get(position)));
        holder.note_txt.setText(String.valueOf(note.get(position)));
        holder.date_txt.setText(String.valueOf(date.get(position)));
        holder.dis_txt.setText(String.valueOf(dis.get(position)));
        //Recyclerview onClickListener
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(note_id.get(position)));
                intent.putExtra("note", String.valueOf(note.get(position)));
                intent.putExtra("date", String.valueOf(date.get(position)));
                intent.putExtra("dis", String.valueOf(dis.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });


    }

    @Override
    public int getItemCount() {
        return note_id.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView note_id_txt, note_txt, date_txt, dis_txt;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            note_id_txt = itemView.findViewById(R.id.note_id_txt);
            note_txt = itemView.findViewById(R.id.note_txt);
            date_txt = itemView.findViewById(R.id.date_txt);
            dis_txt = itemView.findViewById(R.id.dis_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            //Animate Recyclerview
            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }

    }

}
