package com.example.leadmen.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


import com.example.leadmen.Fraguments.ExpenseFragment;
import com.example.leadmen.Fraguments.NewBranchFragment;
import com.example.leadmen.Fraguments.NewBusinessFragment;
import com.example.leadmen.Fraguments.NewLineFragment;
import com.example.leadmen.Fraguments.ProfileFragment;
import com.example.leadmen.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class DashboardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    public static final String GOOGLE_ACCOUNT = "google_account";
    private TextView TextMessage;
    BottomNavigationView navigationView;
    Fragment fragment = null;
    Toolbar toolbar;
    private GoogleSignInClient googleSignInClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Dashboard");

        loadFragment(new NewBusinessFragment());

        navigationView = findViewById(R.id.bottom_nav_view);
        TextMessage = findViewById(R.id.message);
        navigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        //Logout From Google Accounts
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getResources().getString(R.string.web_client_id))
                .build();
        googleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    //Bottom NavigationView
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.new_business:
                    fragment = new NewBusinessFragment();
                    getSupportActionBar().setTitle("Dashboard");
                    break;
                case R.id.new_line:
                    fragment = new NewLineFragment();
                    break;
                case R.id.new_branch:
                    fragment = new NewBranchFragment();
                    break;
                case R.id.profile:
                    fragment = new ProfileFragment();
                    getSupportActionBar().setTitle("Profile");
                    break;
            }
            return loadFragment(fragment);
        }
    };

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK)
            switch (requestCode) {
                case 101:
                    try {
                        // The Task returned from this call is always completed, no need to attach
                        // a listener.
                        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                        GoogleSignInAccount account = task.getResult(ApiException.class);
                        String idToken = account.getIdToken();
                        /*
                         Write to the logic send this id token to server using HTTPS
                         */
                    } catch (ApiException e) {
                        // The ApiException status code indicates the detailed failure reason.
                        Log.w("response", "signInResult:failed code=" + e.getStatusCode());
                    }
                    break;
            }
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_item, menu);
        return true;
    }
*/
    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.bottom_nav_view) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.my_loans:
                Intent my_loans_i = new Intent(DashboardActivity.this,MyLoansActivity.class);
                startActivity(my_loans_i);
                break;
            case R.id.request_form:
                Intent request_form_i = new Intent(DashboardActivity.this,RequsetFormActivity.class);
                startActivity(request_form_i);
                break;
            case R.id.profile:
                fragment = new ProfileFragment();
                getSupportActionBar().setTitle("Profile");
                //Intent profile_i = new Intent(DashboardActivity.this,ProfileActivity.class);
                //startActivity(profile_i);
                break;
            case R.id.reports:
                Intent reports_i = new Intent(DashboardActivity.this,ReportActivity.class);
                startActivity(reports_i);
                fragment = new ExpenseFragment();
                break;
            case R.id.collections:
                Intent collections_i = new Intent(DashboardActivity.this,CollectionsActivity.class);
                startActivity(collections_i);
                fragment = new NewBranchFragment();
                break;
            case R.id.new_loans:
                Intent new_loans_i = new Intent(DashboardActivity.this,NewLoanActivity.class);
                startActivity(new_loans_i);
                fragment = new ExpenseFragment();
                break;
            case R.id.daily:
                Intent daily_i = new Intent(DashboardActivity.this,DailyActivity.class);
                startActivity(daily_i);
                fragment = new NewBranchFragment();
                break;
            case R.id.new_line:
                Intent new_line_i = new Intent(DashboardActivity.this,NewLineActivity.class);
                startActivity(new_line_i);
                fragment = new ExpenseFragment();
                break;
            case R.id.top_up:
                Intent top_up_i = new Intent(DashboardActivity.this,TopupActivity.class);
                startActivity(top_up_i);
                fragment = new ExpenseFragment();
                break;
            case R.id.new_branch:
                Intent new_branch_i = new Intent(DashboardActivity.this,NewBranchActivity.class);
                startActivity(new_branch_i);
                fragment = new NewBranchFragment();
                break;
            case R.id.add_new_branch:
                Intent add_new_branch_i = new Intent(DashboardActivity.this,AddNewBranchActivity.class);
                startActivity(add_new_branch_i);
                fragment = new ExpenseFragment();
                break;
            case R.id.expense:
                Intent expense_i = new Intent(DashboardActivity.this,ExpenseActivity.class);
                startActivity(expense_i);
                break;
            case R.id.logout:
                googleSignInClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        //On Succesfull signout we navigate the user back to LoginActivity
                        Intent intent=new Intent(DashboardActivity.this,MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                });
                break;
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //set the fragument in bottom navigation view
    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }
}