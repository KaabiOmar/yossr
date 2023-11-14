package tn.esprit.tourbuddy.ui.claims;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import tn.esprit.tourbuddy.MainActivity;
import tn.esprit.tourbuddy.R;
import tn.esprit.tourbuddy.adapter.ClaimsAdapter;
import tn.esprit.tourbuddy.database.AppDataBase;
import tn.esprit.tourbuddy.databinding.FragmentClaimsBinding;
import tn.esprit.tourbuddy.entity.Claims;

public class ClaimsFragment extends Fragment {

    private FragmentClaimsBinding binding;
    private View view;
    private RecyclerView recyclerView;
    private ClaimsAdapter claimsAdapter;
    private ListView listView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentClaimsBinding.inflate(inflater, container, false);
        view = binding.getRoot();

        recyclerView = view.findViewById(R.id.recyclerViewClaims);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        claimsAdapter = new ClaimsAdapter(new ArrayList<>(),this::onDeleteClick,this::onEditClick); // Initialize with an empty list
        recyclerView.setAdapter(claimsAdapter);

        Button btnAddClaims = view.findViewById(R.id.buttonNewClaim);
        btnAddClaims.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAddClaimsClick(v);
            }
        });

        ClaimsViewModel slideshowViewModel =
                new ViewModelProvider(this).get(ClaimsViewModel.class);

        final TextView textView = binding.textTitle;
        slideshowViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        // Load claims data when the fragment is created
        LoadClaimsAsync loadClaimsAsync = new LoadClaimsAsync();
        loadClaimsAsync.execute();

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private class LoadClaimsAsync extends AsyncTask<Void, Void, List<Claims>> {
        @Override
        protected List<Claims> doInBackground(Void... voids) {
            return AppDataBase.getInstance(requireContext()).claimsDao().ListClaims();
        }

        @Override
        protected void onPostExecute(List<Claims> claims) {
            super.onPostExecute(claims);

            // Update the adapter's data with the loaded claims
            claimsAdapter.setClaimList(claims);

            // Notify the adapter that the data set has changed
            claimsAdapter.notifyDataSetChanged();

            Log.d("ClaimsFragment", "Claims loaded successfully");
        }
    }

    private void onAddClaimsClick(View v) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    // Create a Properties object
                    Properties properties = new Properties();
                    properties.put("mail.smtp.host", "smtp.gmail.com");
                    properties.put("mail.smtp.port", "587");
                    properties.put("mail.smtp.starttls.enable", "true");
                    properties.put("mail.smtp.auth", "true");

                    // Sender's email and password
                    String stringSenderEmail = "youssefbazdeh99@gmail.com";
                    String stringPasswordSenderEmail = "npek vcgw ndjk rroc";

                    // Create a Session instance with the properties and an Authenticator
                    Session session = Session.getInstance(properties, new Authenticator() {
                        @Override
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(stringSenderEmail, stringPasswordSenderEmail);
                        }
                    });

                    // Create a MimeMessage
                    MimeMessage mimeMessage = new MimeMessage(session);

                    // Set the sender's email
                    mimeMessage.setFrom(new InternetAddress(stringSenderEmail));

                    // Set the recipient's email
                    mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress("omar.kaabi@esprit.tn"));

                    // Set the email subject and text
                    mimeMessage.setSubject("Subject: Android app mail");
                    mimeMessage.setText("Hello, this is a test email from your Android app.");

                    // Use Transport to send the email
                    Transport.send(mimeMessage);

                    Log.d("MailSender", "Email sent successfully");
                } catch (MessagingException e) {
                    e.printStackTrace();
                    Log.e("MailSender", "Error sending email: " + e.getMessage());
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);
                navController.navigate(R.id.nav_new_claims);


            }
        }.execute();
    }

    private void onEditClick(int position) {
        Claims claimToEdit = claimsAdapter.getClaims().get(position);

        Intent intent = new Intent(getActivity(), ClaimsEdit.class);
        intent.putExtra("claimToEdit", claimToEdit);
        startActivity(intent);
        Log.d("","edit activity");
        Log.d("ClaimsFragment", "Claim to edit: " + claimToEdit.toString());

    }

    @Override
    public void onResume() {
        super.onResume();
        LoadClaimsAsync loadClaimsAsync = new LoadClaimsAsync();
        loadClaimsAsync.execute();
    }

    private void onDeleteClick(int position) {
        Claims claimToDelete = claimsAdapter.getClaims().get(position);

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                AppDataBase.getInstance(requireContext()).claimsDao().supprimerClaims(claimToDelete);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                // Rafraîchir la liste après la suppression

                LoadClaimsAsync loadClaimsAsync = new LoadClaimsAsync();
                loadClaimsAsync.execute();
            }
        }.execute();
    }
}
