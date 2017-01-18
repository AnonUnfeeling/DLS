package com.example.jdroidcoder.dls_dev.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.jdroidcoder.dls_dev.GMailSender;
import com.example.jdroidcoder.dls_dev.R;
import com.example.jdroidcoder.dls_dev.fragments.AboutYouFragment;

/**
 * Created by jdroidcoder on 17.01.17.
 */
public class CreateOrderActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {
    private AboutYouFragment aboutYouFragment = null;
    private FragmentManager fragmentManager = null;
    private FloatingActionButton fab;
    private Bundle bundle = new Bundle();
    private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_order);
        if (fragmentManager == null) {
            fragmentManager = getSupportFragmentManager();
        }
        aboutYouFragment = new AboutYouFragment();
        fragmentManager.beginTransaction().replace(R.id.container, aboutYouFragment).commit();
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);
        setUpToolbar();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onClick(View v) {
        bundle = aboutYouFragment.getAboutYou();
        new Sender().execute();
    }

    private void setUpToolbar() {
        toolbar = (Toolbar) findViewById(R.id.registrationToolbar);
        toolbar.setTitle(getResources().getString(R.string.app_name));
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        setSupportActionBar(toolbar);
    }

    private String createMessageText() {
        String message = "";
        message = "Меня зовут: " + bundle.getString("contactFace");
        message += "\nМоя компания занимается: " + bundle.getString("aboutYou");
        message += "\nЦелевая аудитория моей компании: " + bundle.getString("targetAudience");
        message += "\nМне нужно приложения от вас для: " + bundle.getString("applicationCreationReason");
        //check platforms
        String platforms = "";
        platforms += (bundle.getBoolean("android")) ? "android," : "";
        platforms += (bundle.getBoolean("apple")) ? "Apple iOS," : "";
        platforms += (bundle.getBoolean("windows")) ? "Windows Phone," : "";
        platforms += (bundle.getBoolean("site")) ? "для приложения необходим сайт" : "";
        message += "\nМне нужны такие платформы: " + platforms;
        //check functions
        String functions = "";
        functions += (bundle.getBoolean("paid")) ? "\nВ приложении будет платный контент," : "";
        functions += (bundle.getBoolean("push")) ? "\nPUSH уведомления," : "";
        functions += (bundle.getBoolean("location")) ? "\nКарты или определение местоположение пользователя," : "";
        functions += (bundle.getBoolean("camera")) ? "\nРабота с камерой телефона," : "";
        functions += (bundle.getBoolean("pass")) ? "\nРегистрация / Авторизация пользователя по паролю," : "";
        functions += (bundle.getBoolean("social")) ? "\n Регистрация / Авторизация пользователя через соцсети," : "";
        functions += (bundle.getBoolean("controlSystem")) ? "\nНеобходимо создать систему управления приложением" : "";
        message += "\nМне нужны такие функции: " + functions;
        //check api
        String api = "";
        api += (bundle.getBoolean("noApi")) ? "Не нужен," : "";
        api += (bundle.getBoolean("fromYou")) ? "Необходима разработка с вашей стороны," : "";
        api += (bundle.getBoolean("fromMe")) ? "Мы разработаем/разработали своими силами" : "";
        message += "\n Web API: " + api;
        //check garant
        message += (bundle.getBoolean("garant")) ? "\nНам необходимо техническое обслуживание после гарантийного" : "";
        //contact info
        message += "\nКонтактная информация: ";
        message += "\nТелефон: " + bundle.getString("contactPhone");
        message += "\nE-mail: " + bundle.getString("contactEmail");
        message += "\nWeb-сайт компании: " + bundle.getString("contactSite");
        message += "\nКонтактное лицо: " + bundle.getString("contactFace");

        return message;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.home) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        } else if (id == R.id.createOrder) {
            moveToCreateOrderActivity();
        } else if (id == R.id.contacts) {
            AlertDialog.Builder builder;
            String[] points = new String[]{"Телефоны", "Емейлы"};
            builder = new AlertDialog.Builder(CreateOrderActivity.this);
            builder.setItems(points, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int item) {
                    if (item == 0) {
                        AlertDialog.Builder builder;
                        final String[] phones = new String[]{"0939133710", "0967545215", "0501373907"};
                        builder = new AlertDialog.Builder(CreateOrderActivity.this);
                        builder.setItems(phones, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int item) {
                                try {
                                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                                    callIntent.setData(Uri.parse("tel:" + phones[item]));
                                    startActivity(callIntent);
                                } catch (SecurityException ex) {
                                    Intent callIntentM = new Intent(Intent.ACTION_DIAL);
                                    callIntentM.setData(Uri.parse("tel:" + phones[item]));
                                    startActivity(callIntentM);
                                }
                            }
                        });

                        AlertDialog alert = builder.create();
                        alert.show();
                    } else if (item == 1) {
                        Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
                        emailIntent.setType("plain/text");
                        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"diglabstudio@gmail.com"});
                        startActivity(Intent.createChooser(emailIntent, "Отправка письма..."));
                    }
                }
            });

            AlertDialog alert = builder.create();
            alert.show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void moveToCreateOrderActivity() {
        startActivity(new Intent(this, CreateOrderActivity.class));
        finish();
    }

    private class Sender extends AsyncTask<Void, Void, Integer> {
        @Override
        protected Integer doInBackground(Void... params) {
            GMailSender sender = new GMailSender("sendorder123", "qwerty123321");
            try {
                sender.sendMail("Прошу принять заказ", createMessageText(), "sendorder123@gmail.com", "anonunfeeling1@gmail.com");
                return 0;
            } catch (Exception e) {
                e.printStackTrace();
                return 1;
            }
        }

        @Override
        protected void onPostExecute(Integer aVoid) {
            if (aVoid == 0) {
                Toast.makeText(CreateOrderActivity.this, "Сообщение отправлено", Toast.LENGTH_LONG).show();
            } else if (aVoid == 1) {
                Toast.makeText(CreateOrderActivity.this, "Неизвестная ошибка", Toast.LENGTH_LONG).show();
            }
            super.onPostExecute(aVoid);
        }
    }

}
