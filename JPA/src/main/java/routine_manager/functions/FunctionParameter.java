package routine_manager.functions;

import jakarta.persistence.ParameterMode;

import java.util.Map;

public record FunctionParameter(String paramName, Class<?> clazz, ParameterMode mode) {

    public static Map<String, FunctionParameter[]> parametersMap = Map.of(
            "criarJogador",
            new FunctionParameter[]{
                    new FunctionParameter("nomeJogador", String.class, ParameterMode.IN),
                    new FunctionParameter("emailJogador", String.class, ParameterMode.IN),
                    new FunctionParameter("regiaoJogador", String.class, ParameterMode.IN)
            },
            "desativarJogador",
            new FunctionParameter[]{
                    new FunctionParameter("idJogador", int.class, ParameterMode.IN)
            },
            "banirJogador",
            new FunctionParameter[]{
                    new FunctionParameter("idJogador", int.class, ParameterMode.IN)
            },
            "totalPontosJogador",
            new FunctionParameter[]{
                    new FunctionParameter("idJogador", int.class, ParameterMode.IN),
                    new FunctionParameter("totalPontos", int.class, ParameterMode.OUT)
            },
            "totalJogosJogador",
            new FunctionParameter[]{
                    new FunctionParameter("idJogador", int.class, ParameterMode.IN),
                    new FunctionParameter("totalJogos", int.class, ParameterMode.OUT)
            },
            "PontosJogoPorJogador",
            new FunctionParameter[]{
                    //new FunctionParameter(String.class, ParameterMode.IN)
            },
            "associarCracha",
            new FunctionParameter[]{
                    new FunctionParameter("idJogador", int.class, ParameterMode.IN),
                    new FunctionParameter("idJogo", String.class, ParameterMode.IN),
                    new FunctionParameter("crachaNome", String.class, ParameterMode.IN)
            },
            "iniciarConversa",
            new FunctionParameter[]{
                    new FunctionParameter("idJogador", int.class, ParameterMode.IN),
                    new FunctionParameter("nomeConversa", String.class, ParameterMode.IN),
                    new FunctionParameter("res", int.class, ParameterMode.OUT)
            },
            "juntarConversa",
            new FunctionParameter[]{
                    new FunctionParameter("idJogador", int.class, ParameterMode.IN),
                    new FunctionParameter("idConversa", int.class, ParameterMode.IN)
            },
            "enviarMensagem",
            new FunctionParameter[]{
                    new FunctionParameter("idJogador", int.class, ParameterMode.IN),
                    new FunctionParameter("idConversa", int.class, ParameterMode.IN),
                    new FunctionParameter("texto", String.class, ParameterMode.IN)
            }
            //"jogadorTotalInfo",
            //new FunctionParameter[]{
            //}
    );
}
