package com.eis.medihubdcr.Activity;

import android.app.ActivityOptions;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.eis.medihubdcr.Fragment.DCREntry;
import com.eis.medihubdcr.Fragment.Elearning;
import com.eis.medihubdcr.Fragment.Help;
import com.eis.medihubdcr.Fragment.MTPConfirmation;
import com.eis.medihubdcr.Fragment.Options;
import com.eis.medihubdcr.Fragment.UploadVisitingCard;
import com.eis.medihubdcr.Fragment.VisitPlanDocLst;
import com.eis.medihubdcr.Others.Global;
import com.eis.medihubdcr.R;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView ename, hqname, wdate;
    String whichmth = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorPrimary));


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        ename = headerView.findViewById(R.id.name);
        hqname = headerView.findViewById(R.id.hqname);
        wdate = headerView.findViewById(R.id.wdate);

        ename.setText(Global.ename);
        hqname.setText("HQ : " + Global.hname);
        wdate.setText("WRK DATE : " + Global.date);

        //NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        if (getIntent().getStringExtra("openfrag").equalsIgnoreCase("dcr")) {
            displaySelectedScreen(R.id.nav_dcr);
        } else if (getIntent().getStringExtra("openfrag").equalsIgnoreCase("visitingcard")) {
            displaySelectedScreen(R.id.nav_file_upload);
        } else if (getIntent().getStringExtra("openfrag").equalsIgnoreCase("home")) {
            displaySelectedScreen(R.id.nav_home);
        } else if (getIntent().getStringExtra("openfrag").equalsIgnoreCase("mtp")) {
            displaySelectedScreen(R.id.nav_mtp);
        } else if (getIntent().getStringExtra("openfrag").equalsIgnoreCase("visitplansum")) {
            displaySelectedScreen(R.id.nav_vps);
        } else if (getIntent().getStringExtra("openfrag").equalsIgnoreCase("elearn")) {
            displaySelectedScreen(R.id.nav_eln);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            //super.onBackPressed();
            AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
            builder.setCancelable(true);
            builder.setTitle("EXIT ?");
            builder.setMessage("Do you want to Exit ?");
            builder.setPositiveButton("EXIT", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                    HomeActivity.this.overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out);
                }
            });
            builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            AlertDialog dialog2 = builder.create();
            dialog2.show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.mlogout) {
            logoutAlert();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void displaySelectedScreen(int itemId) {

        //creating fragment object
        Fragment fragment = null;

        //initializing the fragment object which is selected
        switch (itemId) {
            case R.id.nav_home:
                fragment = new Options();
                break;
            case R.id.nav_dcr:

                //new Global().notAllowed(HomeActivity.this);
                //if (Global.ecode.equalsIgnoreCase("01349") || Global.ecode.equalsIgnoreCase("01511") || Global.ecode.equalsIgnoreCase("01723") || Global.ecode.equalsIgnoreCase("01809") || Global.ecode.equalsIgnoreCase("02042") || Global.ecode.equalsIgnoreCase("02712")) {
                    fragment = new DCREntry();
                /*} else {
                    new Global().notAllowed(HomeActivity.this);
                }*/
                break;
            case R.id.nav_file_upload:
                fragment = new UploadVisitingCard();
                //new Global().notAllowed(HomeActivity.this);
                break;
            case R.id.nav_mtp:
                //new Global().notAllowed(HomeActivity.this);
                //if (Global.ecode.equalsIgnoreCase("01349") || Global.ecode.equalsIgnoreCase("01511") || Global.ecode.equalsIgnoreCase("01723") || Global.ecode.equalsIgnoreCase("01809") || Global.ecode.equalsIgnoreCase("02042") || Global.ecode.equalsIgnoreCase("02712")) {
                    fragment = new MTPConfirmation();
                /*} else {
                    new Global().notAllowed(HomeActivity.this);
                }*/
                break;
            case R.id.nav_vps:
                fragment = new VisitPlanDocLst();
                break;
            case R.id.nav_eln:
                fragment = new Elearning();
                break;
            case R.id.nav_help:
                //new Global().notAllowed(HomeActivity.this);
                fragment = new Help();
                break;
            case R.id.nav_logout:
                logoutAlert();
                break;
        }

        //replacing the fragment
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.setCustomAnimations(R.anim.trans_left_in, R.anim.trans_left_out);
            ft.replace(R.id.dcrentryscreen, fragment);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        displaySelectedScreen(item.getItemId());
        return true;
    }

    public void logoutAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("LOGOUT ?");
        builder.setMessage("Are you sure you want to Logout ?");
        builder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Global.password = null;
                        Intent intent = new Intent(HomeActivity.this, LoginScreen.class);
                        Bundle bndlanimation = ActivityOptions.makeCustomAnimation(HomeActivity.this, R.anim.trans_right_in, R.anim.trans_right_out).toBundle();
                        startActivity(intent, bndlanimation);
                        finish();
                    }
                });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //do nothing
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void mtpAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
        builder.setCancelable(true);
        builder.setTitle("Alert ?");
        builder.setMessage("Which month of MTP you wants to view ?");
        builder.setPositiveButton("NEXT", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Global.whichmth = "NEXT";
                //fragment = new MTPConfirmation();
            }
        });
        builder.setNeutralButton("CURRENT", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //new Global().notAllowed(getActivity());
                Global.whichmth = "CURRENT";
                //fragment = new MTPConfirmation();
            }
        });
        AlertDialog dialog2 = builder.create();
        dialog2.show();
    }

}
