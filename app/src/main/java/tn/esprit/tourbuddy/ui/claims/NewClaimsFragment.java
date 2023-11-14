package tn.esprit.tourbuddy.ui.claims;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import tn.esprit.tourbuddy.R;
import tn.esprit.tourbuddy.dao.ClaimsDao;
import tn.esprit.tourbuddy.database.AppDataBase;
import tn.esprit.tourbuddy.entity.Claims;

public class NewClaimsFragment  extends Fragment {
    private TextView textTitle;
    private EditText editTextDescription;
    private EditText editTextSubject;
    private Button buttonSubmit;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_claims, container, false);


        textTitle = view.findViewById(R.id.textTitle);
        editTextDescription = view.findViewById(R.id.editTextDescription);
        editTextSubject = view.findViewById(R.id.editTextSubject);
        buttonSubmit = view.findViewById(R.id.buttonSubmit);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String description = editTextDescription.getText().toString();
                String subject = editTextSubject.getText().toString();

                if (!description.isEmpty() && !subject.isEmpty()) {
                    if (addClaimToDatabase(description, subject)) {
                        Toast.makeText(requireContext(), "Réclamation ajoutée avec succès !", Toast.LENGTH_SHORT).show();
                        getParentFragmentManager().popBackStack();
                    } else {
                        Toast.makeText(requireContext(), "Erreur lors de l'ajout de la réclamation.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(requireContext(), "DES CHAMPS VIDES !.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
    private static class InsertClaimsAsyncTask extends AsyncTask<Claims, Void, Long> {
        private ClaimsDao claimsDao;
        private onClaimsInsertedListener listener;

        public InsertClaimsAsyncTask(ClaimsDao claimsDao, onClaimsInsertedListener listener) {
            this.claimsDao = claimsDao;
            this.listener = listener;
        }

        @Override
        protected Long doInBackground(Claims... claims) {
            // Insertion dans la base de données
            return claimsDao.insererClaims(claims[0]);
        }

        @Override
        protected void onPostExecute(Long result) {
            // Appel du callback pour informer du résultat de l'insertion
            if (listener != null) {
                listener.onClaimsInserted(result);
            }
        }
    }

    // Interface pour écouter le résultat de l'insertion
    public interface OnClaimsInsertedListener {
        void onClaimsInserted(Long result);
    }
    private boolean addClaimToDatabase(String description, String subject) {
        try {
            AppDataBase appDataBase = AppDataBase.getInstance(requireContext());
            ClaimsDao claimsDao = appDataBase.claimsDao();

            // Créer une instance de Claims avec les données fournies
            Claims claim = new Claims();
            claim.setRec(description);
            claim.setSujet(subject);

            // Utiliser AsyncTask pour effectuer l'insertion dans un thread séparé
            new InsertClaimsAsyncTask(claimsDao, new onClaimsInsertedListener() {
                @Override
                public void onClaimsInserted(Long result) {
                    if (isAdded()) {  // Vérifier si le fragment est toujours attaché
                        if (result > 0) {
                            Toast.makeText(requireActivity(), "Réclamation ajoutée avec succès !", Toast.LENGTH_SHORT).show();
                            getParentFragmentManager().popBackStack();
                        } else {
                            Toast.makeText(requireActivity(), "Erreur lors de l'ajout de la réclamation.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }




            }).execute(claim);

            // Retourner true pour indiquer que la tâche a été démarrée avec succès
            return true;
        } catch (Exception e) {
            Log.e("NewClaimsFragment", "Erreur lors de l'ajout dans la base de données", e);
            // Retourner false pour indiquer qu'une erreur s'est produite
            return false;
        }
    }

    // Interface pour écouter le résultat de l'insertion
    public interface onClaimsInsertedListener {
        void onClaimsInserted(Long result);
    }
}