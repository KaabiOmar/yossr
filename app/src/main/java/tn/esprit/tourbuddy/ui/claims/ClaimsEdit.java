package tn.esprit.tourbuddy.ui.claims;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import tn.esprit.tourbuddy.R;
import tn.esprit.tourbuddy.database.AppDataBase;
import tn.esprit.tourbuddy.entity.Claims;

public class ClaimsEdit extends AppCompatActivity {

    private EditText editClaimSubjectEditText;
    private EditText editClaimDescriptionEditText;
    private Button editButton;
    private Claims claimToEdit;
    private Button cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_claims_edit);

        editClaimSubjectEditText = findViewById(R.id.editClaimSubjectEditText);
        editClaimDescriptionEditText = findViewById(R.id.editClaimDescriptionEditText);
        editButton = findViewById(R.id.editClaimButton);
        editButton.setOnClickListener(v -> onUpdateButtonClick());
        cancelButton = findViewById(R.id.cancelEditButton);  // Initialize cancelButton
        cancelButton.setOnClickListener(v -> onCancelButtonClick());  // Set OnClickListener for cancelButton


        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("claimToEdit")) {
            claimToEdit = intent.getParcelableExtra("claimToEdit");
            if (claimToEdit != null) {
                // Remplir automatiquement les champs avec les détails du produit
                editClaimSubjectEditText.setText(claimToEdit.getSujet());
                editClaimDescriptionEditText.setText(claimToEdit.getRec());
            }
        }
    }

    private void onUpdateButtonClick() {
        // Obtenez les valeurs modifiées des champs
        String newSubject = editClaimSubjectEditText.getText().toString();
        String newDescription = editClaimDescriptionEditText.getText().toString();

        // Mettez à jour l'objet Claims avec les nouvelles valeurs
        Claims updatedClaim = claimToEdit;
        updatedClaim.setSujet(newSubject);
        updatedClaim.setRec(newDescription);

        // Pass the context to the AsyncTask
        new UpdateClaimsAsyncTask(this, updatedClaim).execute();
    }

    private void onCancelButtonClick() {
        // Close the activity when the "Cancel" button is clicked
        finish();
    }
    private class UpdateClaimsAsyncTask extends AsyncTask<Void, Void, Void> {

        private final AppDataBase appDataBase;
        private final Claims updatedClaim;
        private final Context context;

        UpdateClaimsAsyncTask(Context context, Claims updatedClaim) {
            this.context = context;
            this.appDataBase = AppDataBase.getInstance(context);
            this.updatedClaim = updatedClaim;
        }




        @Override
        protected Void doInBackground(Void... voids) {
            appDataBase.claimsDao().modifierClaims(updatedClaim);
            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            // Handle the result after the update
            // You can show an AlertDialog or perform other actions

            new AlertDialog.Builder(context)
                    .setTitle("Modification réussie")
                    .setMessage("Le reclamation a été modifié avec succès.")
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Close the activity or perform other actions
                            ((ClaimsEdit) context).finish();
                        }
                    })
                    .show();
        }
    }
}