package br.com.rbarreto.firebasecrud.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.rbarreto.firebasecrud.R;
import br.com.rbarreto.firebasecrud.modelo.Tarefa;

public class MeuAdapter extends ArrayAdapter<Tarefa> {

    private Context mContext;
    private List<Tarefa> tarefaLista = new ArrayList<>();


    public MeuAdapter(@NonNull Context context, @SuppressLint("SupportAnnotationUsage") @LayoutRes ArrayList<Tarefa> list){
        super(context, 0, list);
        mContext = context;
        tarefaLista = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null){
            listItem = LayoutInflater.from(mContext).inflate(R.layout.layout_list_custom, parent, false);
        }

        Tarefa tarefaAtual = tarefaLista.get(position);

        TextView nome = listItem.findViewById(R.id.tvName);
        nome.setText(tarefaAtual.getNome());

        TextView status = listItem.findViewById(R.id.tvStatus);
        status.setText(tarefaAtual.getUid());
        return listItem;
    }
}
