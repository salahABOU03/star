package com.example.star.adapter;



import android.app.AlertDialog;
import android.content.Context;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.star.R;
import com.example.star.beans.Star;
import com.example.star.service.StarService;

import java.util.ArrayList;
import java.util.List;

public class StarAdapter extends RecyclerView.Adapter<StarAdapter.StarViewHolder> implements Filterable {
    private static final String TAG = "StarAdapter";
    private List<Star> stars;
    private List<Star> starsFilter;
    private Context context;;
    public StarAdapter(Context context, List<Star> stars) {
        this.stars = stars;
        this.context = context;

        this.starsFilter = new ArrayList<>();
        if (stars != null) {
            this.starsFilter.addAll(stars);
        }

        mfilter = new NewFilter(this);

    }
    //    private List<Star> starsFilter = stars;
    private NewFilter mfilter;
//    @NonNull
//    @Override
//    public StarViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//        View v = LayoutInflater.from(this.context).inflate(R.layout.star_item,
//                viewGroup, false);
//        return new StarViewHolder(v);
//
//
//    }

    @Override
    public StarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.star_item, parent, false);
        final StarViewHolder holder = new StarViewHolder(v);

        holder.itemView.setOnClickListener(view -> {
            View popup = LayoutInflater.from(context).inflate(R.layout.star_edit_item, null, false);

            ImageView img = popup.findViewById(R.id.img);
            RatingBar bar = popup.findViewById(R.id.ratingBar);
            TextView idss = popup.findViewById(R.id.idss);


            Star star = stars.get(holder.getAdapterPosition());


            Glide.with(context)
                    .load(star.getImg())
//                    .placeholder(R.drawable.ic_launcher_foreground)
                    .into(img);


            bar.setRating(star.getStar());
            idss.setText(String.valueOf(star.getId()));


            AlertDialog dialog = new AlertDialog.Builder(context)
                    .setTitle("Note")
                    .setMessage("Donnez une note entre 1 et 5 :")
                    .setView(popup)
                    .setPositiveButton("Valider", (dialog1, which) -> {
                        float newRating = bar.getRating();
                        int starId = Integer.parseInt(idss.getText().toString());


                        Star updatedStar = StarService.getInstance().findById(starId);
                        if (updatedStar != null) {
                            updatedStar.setStar(newRating);
                            StarService.getInstance().update(updatedStar);
                            notifyItemChanged(holder.getAdapterPosition()); // Rafraîchir la liste
                        }
                    })
                    .setNegativeButton("Annuler", null)
                    .create();
            dialog.show();
        });

        holder.itemView.setOnLongClickListener(view -> {
            new AlertDialog.Builder(context)
                    .setTitle("Supprimer")
                    .setMessage("Voulez-vous vraiment supprimer cet acteur ?")
                    .setPositiveButton("Oui", (dialog, which) -> {
                        int position = holder.getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            Star starToRemove = stars.get(position);
                            StarService.getInstance().delete(starToRemove);
                            stars.remove(position);
                            notifyItemRemoved(position);
                        }
                    })
                    .setNegativeButton("Annuler", null)
                    .show();
            return true;
        });


        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull StarViewHolder starViewHolder, int i) {
        Log.d(TAG, "onBindView call ! "+ i);
        Glide.with(context)
                .asBitmap()
                .load(starsFilter.get(i).getImg())
                .apply(new RequestOptions().override(100, 100))
                .into(starViewHolder.img);
        starViewHolder.name.setText(starsFilter.get(i).getName().toUpperCase());
        starViewHolder.stars.setRating(starsFilter.get(i).getStar());
        starViewHolder.idss.setText(starsFilter.get(i).getId()+"");
    }
    @Override
    public int getItemCount() {
        return starsFilter.size();
    }
    @Override
    public Filter getFilter() {
        return mfilter;
    }
    public class StarViewHolder extends RecyclerView.ViewHolder {
        TextView idss;
        ImageView img;
        TextView name;
        RatingBar stars;
        RelativeLayout parent;
        public StarViewHolder(@NonNull View itemView) {
            super(itemView);
            idss = itemView.findViewById(R.id.ids);
            img = itemView.findViewById(R.id.img);
            name = itemView.findViewById(R.id.name);
            stars = itemView.findViewById(R.id.stars);
            parent = itemView.findViewById(R.id.parent);
        }
    }

    //    filter
    public class NewFilter extends Filter {
        public RecyclerView.Adapter mAdapter;
        public NewFilter(RecyclerView.Adapter mAdapter) {
            super();
            this.mAdapter = mAdapter;
        }
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            starsFilter.clear();
            final FilterResults results = new FilterResults();
            if (charSequence.length() == 0) {
                starsFilter.addAll(stars);
            }
            else {
                final String filterPattern = charSequence.toString().toLowerCase().trim();
                for (Star p : stars) {
                    if (p.getName().toLowerCase().startsWith(filterPattern)) {
                        starsFilter.add(p);
                    }
                }
            }
            results.values = starsFilter;
            results.count = starsFilter.size();
            return results;
        }
        @Override protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            starsFilter = (List<Star>) filterResults.values;
            this.mAdapter.notifyDataSetChanged();
        }
    }




}