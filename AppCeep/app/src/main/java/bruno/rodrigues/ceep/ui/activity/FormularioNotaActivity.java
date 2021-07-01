package bruno.rodrigues.ceep.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import org.jetbrains.annotations.NotNull;

import bruno.rodrigues.ceep.R;
import bruno.rodrigues.ceep.model.Nota;

import static bruno.rodrigues.ceep.ui.activity.Constantes.CHAVE_NOTA;

public class FormularioNotaActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_nota);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_formulario_nota_salva, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_salva_nota) {
            Nota nota = criaNota();
            retornaNota(nota);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    private void retornaNota(Nota nota) {
        Intent intent = new Intent();
        intent.putExtra(CHAVE_NOTA, nota);
        setResult(2, intent);
    }

    @NotNull
    private Nota criaNota() {
        EditText titulo = findViewById(R.id.formulario_nota_titulo);
        EditText descricao = findViewById(R.id.formulario_nota_descricao);
        Nota nota = new Nota(titulo.getText().toString(), descricao.getText().toString());
        return nota;
    }
}