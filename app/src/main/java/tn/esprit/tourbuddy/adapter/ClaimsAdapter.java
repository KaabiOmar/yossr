package tn.esprit.tourbuddy.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import tn.esprit.tourbuddy.R;
import tn.esprit.tourbuddy.entity.Claims;

public class ClaimsAdapter extends RecyclerView.Adapter<ClaimsAdapter.ClaimViewHolder> {

    private List<Claims> claimList;
    private OnDeleteClickListener deleteClickListener;
    private OnEditClickListener editClickListener;

    public interface OnDeleteClickListener {
        void onDeleteClick(int position);
    }
    public interface OnEditClickListener {
        void onEditClick(int position);
    }

    public ClaimsAdapter(List<Claims> claimList,OnDeleteClickListener deleteClickListener,OnEditClickListener editClickListener) {
        this.claimList = claimList;
        this.editClickListener = editClickListener;
        this.deleteClickListener = deleteClickListener;
    }

    @NonNull
    @Override
    public ClaimViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_claims, parent, false);
        Log.d("list",claimList.toString());
        return new ClaimViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClaimViewHolder holder, int position) {
        Claims claim = claimList.get(position);
        holder.bind(claim);
        // Gestionnaire de clic pour le bouton Edit
        holder.editButton.setOnClickListener(v -> {
            Log.d("","ediiit");
            if (editClickListener != null) {
                editClickListener.onEditClick(position);
            }
        });
        holder.deleteButton.setOnClickListener(v -> {
            if (deleteClickListener != null) {
                deleteClickListener.onDeleteClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return claimList.size();
    }
    public List<Claims> getClaims() {
        return claimList;
    }
    public class ClaimViewHolder extends RecyclerView.ViewHolder {

        private TextView textSubject;
        private TextView textDescription;
        Button deleteButton;
        Button editButton;

        public ClaimViewHolder(@NonNull View itemView) {
            super(itemView);
            textSubject = itemView.findViewById(R.id.textViewSubject);
            textDescription = itemView.findViewById(R.id.textViewDescription);
        }

        public void bind(Claims claim) {
            editButton = itemView.findViewById(R.id.editButton);
            textSubject.setText(claim.getSujet());
            textDescription.setText(claim.getRec());
            deleteButton = itemView.findViewById(R.id.deleteButton);
        }
    }

    public void setClaimList(List<Claims> claimList) {
        this.claimList = claimList;
        notifyDataSetChanged();
    }
}
