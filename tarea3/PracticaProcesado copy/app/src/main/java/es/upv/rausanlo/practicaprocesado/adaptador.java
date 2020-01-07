package es.upv.rausanlo.practicaprocesado;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;

public class Adaptador extends FirestoreRecyclerAdapter<Clase, Adaptador.ViewHolder>{
    private Context context;
    protected View.OnClickListener onClickListener;
    public Adaptador(Context context,
                     @NonNull FirestoreRecyclerOptions<Clase> options) {
        super(options);
        this.context = context.getApplicationContext();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView campo1;
        public final TextView campo2;
        public ViewHolder(View itemView) {
            super(itemView);
            this.campo1 = (TextView) itemView.findViewById(R.id.textView1);
            this.campo2 = (TextView) itemView.findViewById(R.id.textView2);
        }
    }
    @Override public Adaptador.ViewHolder onCreateViewHolder(
            ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.elemento_lista, parent, false);
        return new Adaptador.ViewHolder(view);
    }
    @Override protected void onBindViewHolder(@NonNull Adaptador
            .ViewHolder holder, int position, @NonNull Clase objeto) {
        holder.campo1.setText(getSnapshots().getSnapshot(position).getId());
        holder.campo2.setText(new DecimalFormat("#.0").format(objeto.getCampo1())+"º");
        holder.itemView.setOnClickListener(onClickListener);
    }
    public void setOnItemClickListener(View.OnClickListener onClick) {
        onClickListener = onClick;
    }
}