package tn.esprit.tourbuddy.ui.forum;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import tn.esprit.tourbuddy.R;
import tn.esprit.tourbuddy.database.AppDataBase;
import tn.esprit.tourbuddy.entity.Publication;

public class ForumFragment extends Fragment {

    private ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forum, container, false);

        Button btnAddPublication = view.findViewById(R.id.btn_add_publication);
        btnAddPublication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAddPublicationClick(v);
            }
        });
        new LoadPublicationsAsync().execute();

        return view;
    }

    private class LoadPublicationsAsync extends AsyncTask<Void, Void, List<Publication>> {
        @Override
        protected List<Publication> doInBackground(Void... voids) {
            return AppDataBase.getInstance(requireContext()).publicationDao().ListPublications();
        }

        @Override
        protected void onPostExecute(List<Publication> publications) {
            super.onPostExecute(publications);

            ArrayList<HashMap<String, String>> listePublications = new ArrayList<>();

            for (Publication publication : publications) {
                HashMap<String, String> map = new HashMap<>();
                map.put("titre", publication.getTitre());
                map.put("contenu", publication.getContenu());
                listePublications.add(map);
            }
            SimpleAdapter adapter = new SimpleAdapter(
                    getContext(),
                    listePublications,
                    android.R.layout.simple_list_item_2,
                    new String[]{"titre", "contenu", "imageP"},
                    new int[]{android.R.id.text1, android.R.id.text2}
            );

            listView = getView().findViewById(R.id.listView);
            listView.setAdapter(adapter);
        }
    }

    private void onAddPublicationClick(View v) {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);
        navController.navigate(R.id.nav_new_publication);
    }
}