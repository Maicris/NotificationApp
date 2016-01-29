package br.com.appdev.notificationapp;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ActionReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        receiver = new ActionReceiver();
    }

    public void btnSimpleNotificationOnClick(View view) {
        int id = 1;
        Intent intent = new Intent(this, MessageActivity.class);
        intent.putExtra("msg", "Notificação Simples recebida!!!");

        TaskStackBuilder builder = TaskStackBuilder.create(this);
        builder.addParentStack(MessageActivity.class);
        builder.addNextIntent(intent);
        PendingIntent p = builder.getPendingIntent(id, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder nBuilder = new NotificationCompat.Builder(this);
        nBuilder.setDefaults(Notification.DEFAULT_ALL);
        nBuilder.setSmallIcon(android.R.drawable.ic_dialog_alert);
        nBuilder.setContentTitle("Nova mensagem");
        nBuilder.setContentText("Você possui uma nova mensagem");
        nBuilder.setContentIntent(p);
        nBuilder.setAutoCancel(true);

        NotificationManagerCompat nmc = NotificationManagerCompat.from(this);
        nmc.notify(id, nBuilder.build());
    }

    public void btnHeadsUpNotificationOnClick (View view) {
        int id = 2;
        Intent intent = new Intent(this, MessageActivity.class);
        intent.putExtra("msg", "Notificação HEADS-UP recebida!!!");

        TaskStackBuilder builder = TaskStackBuilder.create(this);
        builder.addParentStack(MessageActivity.class);
        builder.addNextIntent(intent);
        PendingIntent p = builder.getPendingIntent(id, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder nBuilder = new NotificationCompat.Builder(this);
        nBuilder.setDefaults(Notification.DEFAULT_ALL);
        nBuilder.setSmallIcon(android.R.drawable.ic_dialog_alert);
        nBuilder.setContentTitle("Nova mensagem");
        nBuilder.setContentText("Você possui uma nova mensagem");
        nBuilder.setContentIntent(p);
        nBuilder.setAutoCancel(true);
        nBuilder.setFullScreenIntent(p, false);

        NotificationManagerCompat nmc = NotificationManagerCompat.from(this);
        nmc.notify(id, nBuilder.build());
    }

    public void btnBigNotificationOnClick (View view) {
        int id = 3;
        Intent intent = new Intent(this, MessageActivity.class);
        intent.putExtra("msg", "Notificação BIG recebida!!!");

        TaskStackBuilder builder = TaskStackBuilder.create(this);
        builder.addParentStack(MessageActivity.class);
        builder.addNextIntent(intent);
        PendingIntent p = builder.getPendingIntent(id, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.InboxStyle inboxStyle =
                new NotificationCompat.InboxStyle();
        inboxStyle.setBigContentTitle("Seguem algumas tarefas");
        inboxStyle.addLine("Comprar pão");
        inboxStyle.addLine("Recolher o cocô do cachorro");
        inboxStyle.addLine("Levar o lixo para fora");
        inboxStyle.addLine("Guardar o sapato no armário");
        inboxStyle.setSummaryText("Você possui novas mensagens");

        NotificationCompat.Builder nBuilder = new NotificationCompat.Builder(this);
        nBuilder.setDefaults(Notification.DEFAULT_ALL);
        nBuilder.setSmallIcon(android.R.drawable.ic_dialog_alert);
        nBuilder.setContentTitle("Nova mensagem");
        nBuilder.setContentText("Você possui uma nova mensagem");
        nBuilder.setContentIntent(p);
        nBuilder.setAutoCancel(true);
        nBuilder.setStyle(inboxStyle);
        nBuilder.setNumber(4);

        NotificationManagerCompat nmc = NotificationManagerCompat.from(this);
        nmc.notify(id, nBuilder.build());
    }

    public void btnActionNotificationOnClick (View view) {
        int id = 4;

        Intent intent = new Intent("VIEW_MESSAGE");
        intent.putExtra("notificationID", id);
        PendingIntent p = PendingIntent.getBroadcast(this, 0, intent, 0);

        NotificationCompat.Builder nBuilder = new NotificationCompat.Builder(this);
        nBuilder.setDefaults(Notification.DEFAULT_ALL);
        nBuilder.setSmallIcon(android.R.drawable.ic_media_play);
        nBuilder.setContentTitle("Nome da Música");
        nBuilder.setContentText("Nome do Artista");
        nBuilder.setContentIntent(p);
        nBuilder.setAutoCancel(true);
        nBuilder.addAction(android.R.drawable.ic_media_play, "Play", p);

        NotificationManagerCompat nmc = NotificationManagerCompat.from(this);
        nmc.notify(id, nBuilder.build());
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(receiver, new IntentFilter("VIEW_MESSAGE"));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }

    public class ActionReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "Começou a música", Toast.LENGTH_LONG).show();
            NotificationManagerCompat nmc = NotificationManagerCompat.from(context);
            nmc.cancel(intent.getIntExtra("notificationID", 0));
        }
    }
}
