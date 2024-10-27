package br.team.wtb.Utils.Developer;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.team.wtb.Model.Developer;
import br.team.wtb.R;

public class DeveloperAdapter extends RecyclerView.Adapter<DeveloperAdapter.DeveloperViewHolder> {

    private List<Developer> developerList;

    public DeveloperAdapter(List<Developer> developerList) {
        this.developerList = developerList;
    }

    @NonNull
    @Override
    public DeveloperViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dev_details, parent, false);
        return new DeveloperViewHolder(view);
    }

    // Método chamado para preencher os dados de um item de visualização
    @Override
    public void onBindViewHolder(@NonNull DeveloperViewHolder holder, int position) {

        Developer developer = developerList.get(position);

        holder.lblName.setText(developer.getName());
        holder.lblRole.setText(developer.getRole());
        holder.lblDescription.setText(developer.getDescription());
        holder.imgProfile.setImageResource(developer.getPhotoURL());

        // Setando para quando clicar no botão do GitHub
        holder.btnGithub.setOnClickListener(v -> {

            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(developer.getGithubLink()));
            v.getContext().startActivity(intent);
        });

        // Setando para quando clicar no botão de compartilhar
        holder.btnShare.setOnClickListener(v -> {

            shareDeveloperInfo(v, developer);
        });
    }

    @Override
    public int getItemCount() {
        return developerList.size();
    }

    // Método para compartilhar os dados do Filme
    private void shareDeveloperInfo(View view, Developer developer) {

        String shareText = "WTB: " + "github.com/devduque/WTB" +
                "\nDev from WTB Team:" + developer.toString();

        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, shareText);
        view.getContext().startActivity(Intent.createChooser(shareIntent, "Share developer info via"));
    }

    // Classe interna ViewHolder para manter referências aos componentes de visualização
    static class DeveloperViewHolder extends RecyclerView.ViewHolder {

        ImageView imgProfile;

        TextView lblName;

        TextView lblRole;

        TextView lblDescription;

        ImageView btnGithub;

        ImageView btnShare;

        // Construtor do ViewHolder
        public DeveloperViewHolder(@NonNull View itemView) {

            super(itemView);

            // Inicializa os componentes de visualização
            imgProfile = itemView.findViewById(R.id.img_dev);

            lblName = itemView.findViewById(R.id.lbl_title);

            lblRole = itemView.findViewById(R.id.lbl_role);

            lblDescription = itemView.findViewById(R.id.dev_description);

            btnGithub = itemView.findViewById(R.id.btn_github);

            btnShare = itemView.findViewById(R.id.btn_share);
        }
    }
}
