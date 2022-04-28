package com.example.admissionvido.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.pm.LauncherActivityInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.admissionvido.Interface.IClickItems.IClickUniversity;
import com.example.admissionvido.Models.University;
import com.example.admissionvido.R;

import java.util.List;

public class UniversitiesAdapter extends RecyclerView.Adapter<UniversitiesAdapter.ViewHolder> {

    Context mContext;
    Activity mActivity;
    List<University> universityList;
    IClickUniversity iClickUniversity;

    public UniversitiesAdapter(Activity mActivity, List<University> universityList, IClickUniversity iClickUniversity) {
        this.mActivity = mActivity;
        this.universityList = universityList;
        this.iClickUniversity = iClickUniversity;
    }

    public UniversitiesAdapter(Context mContext, List<University> universityList, IClickUniversity iClickUniversity) {
        this.mContext = mContext;
        this.universityList = universityList;
        this.iClickUniversity = iClickUniversity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.university_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        University university = universityList.get(position);
        holder.universityImg.setImageResource(universityList.get(position).getImg());
        holder.universityName.setText(universityList.get(position).getUniversity());
        holder.cardView.setRadius(20);
        holder.cardView.setOnClickListener(view -> iClickUniversity.onClickUniversity(university));
    }

    @Override
    public int getItemCount() {
        return universityList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        ImageView universityImg;
        TextView universityName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            universityImg = itemView.findViewById(R.id.university_img);
            universityName = itemView.findViewById(R.id.university_name);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }
}
