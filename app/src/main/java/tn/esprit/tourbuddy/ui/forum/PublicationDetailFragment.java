package tn.esprit.tourbuddy.ui.forum;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import tn.esprit.tourbuddy.R;

public class PublicationDetailFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detailpub, container, false);

        Bundle bundle = getArguments();
        if (bundle != null) {
            String titre = bundle.getString("titre");
            String contenu = bundle.getString("contenu");
            String auteur = bundle.getString("auteur");

            TextView textViewTitre = view.findViewById(R.id.text_view_titre);
            TextView textViewContenu = view.findViewById(R.id.text_view_contenu);
            TextView textViewAuteur = view.findViewById(R.id.text_view_auteur);

            textViewTitre.setText(titre);
            textViewContenu.setText(contenu);
            textViewAuteur.setText(auteur);
        }

        return view;
    }
}
