package bruno.rodrigues.ceep.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import bruno.rodrigues.ceep.R;
import bruno.rodrigues.ceep.dao.NotaDAO;
import bruno.rodrigues.ceep.model.Nota;
import bruno.rodrigues.ceep.ui.adapter.ListaNotasAdapter;
import bruno.rodrigues.ceep.ui.adapter.OnItemClickListener;

import static bruno.rodrigues.ceep.ui.activity.Constantes.CHAVE_NOTA;


public class ListaNotasActivity extends AppCompatActivity {

    private ListaNotasAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_notas);

        List<Nota> todasNotas = configuraNotas();
        configuraAdapter(todasNotas);
        configuraBotaoInsereNota();

    }

    private void configuraBotaoInsereNota() {
        TextView insereNota = findViewById(R.id.lista_notas_insere_nota);
        insereNota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vaiParaProximoFormulario();
            }
        });
    }

    private void vaiParaProximoFormulario() {
        Intent intent = new Intent(ListaNotasActivity.this,
                FormularioNotaActivity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        if (requestCode == 1 && resultCode == 2 && data.hasExtra(CHAVE_NOTA)) {
            Nota nota = (Nota) data.getSerializableExtra(CHAVE_NOTA);
            adicionaNota(nota);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void adicionaNota(Nota nota) {
        new NotaDAO().insere(nota);
        adapter.adiciona(nota);
    }

    private List<Nota> configuraNotas() {
        NotaDAO dao = new NotaDAO();
        for(int i = 0; i < 10; i++){
            dao.insere(new Nota("Titulo " + (i+1), "Descricao " + (i+1)));
        }
        return dao.todos();
    }

    private void configuraAdapter(List<Nota> todasNotas) {
        RecyclerView listaDeNotas = findViewById(R.id.recyclerView);
        adapter = new ListaNotasAdapter(todasNotas, this);
        listaDeNotas.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(Nota nota) {
                Toast.makeText(ListaNotasActivity.this, nota.getTitulo(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}