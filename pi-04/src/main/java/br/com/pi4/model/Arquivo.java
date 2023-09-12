package br.com.pi4.model;

import javax.servlet.http.Part;
import java.io.*;
import java.util.Date;

public class Arquivo {

    public String upload(String diretorio, Part part, InputStream arquivoCarregado) throws FileNotFoundException {
        String nomeDoArquivoOriginal = nomeArquivoOriginal(part);
        Long currentTime = new Date().getTime();
        String fileName = currentTime.toString().concat("-").concat(nomeDoArquivoOriginal.replace(" ", ""));
        String caminhoArquivo = diretorio + "/" + fileName;
        File novoArquivo = new File(caminhoArquivo);
        FileOutputStream saida = new FileOutputStream(novoArquivo);
        copiar(arquivoCarregado, saida);
        System.out.println("Imagens adicionadas com sucesso");
        return caminhoArquivo;
    }

    public String nomeArquivoOriginal(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        String[] tokens = contentDisposition.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return "";
    }

    private void copiar(InputStream origem, OutputStream destino) {
        int bite = 0;
        byte[] tamanhoMaximo = new byte[1024 * 1024 * 20];
        try {
            while ((bite = origem.read(tamanhoMaximo)) >= 0) {
                destino.write(tamanhoMaximo, 0, bite);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
