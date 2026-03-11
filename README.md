# TaskMaster

Projeto Android desenvolvido de forma evolutiva por aulas, com **branches cumulativas**.

## Objetivo do fluxo

A ideia é simples:

- cada aula gera **uma branch própria**;
- essa branch sempre nasce da **aula anterior já funcional**;
- ao fazer merge da branch da aula atual, o projeto deve continuar **buildando e funcionando**.

## Regra das branches

Convenção adotada:

- `aula/04-layouts-estaticos`
- `aula/05-recyclerview-lista`
- `aula/06-navigation-component`
- `aula/07-fragments-abas`
- `aula/08-material-theme-fab-cards`
- `aula/09-sqlite`
- `aula/10-room-livedata`
- `aula/11-sharedpreferences`
- `aula/12-retrofit-categorias`
- `aula/13-sync-servidor`
- `aula/14-firebase-analytics`
- `aula/15-firebase-realtime-collab`
- `aula/16-firebase-auth`
- `aula/17-push-notifications`
- `aula/18-workmanager-sync`
- `aula/19-gps-tarefas`
- `aula/20-app-completo`

### Importante

As branches serão criadas **sob demanda**, e não todas de uma vez.

Isso evita:

- branches vazias;
- branches fora de contexto;
- histórico confuso;
- merge de código ainda não implementado.

## Base atual do projeto

Antes de continuar o desenvolvimento das aulas, foi criada uma base técnica para garantir o build:

- branch técnica atual: `chore/gradle-jdk-compativel`

Nessa base:

- o Gradle do projeto está usando o `wrapper` correto;
- a configuração do Gradle JDK da IDE foi alinhada para uma JDK compatível;
- o projeto está buildando com sucesso.

### Observação sobre Android Studio / IntelliJ

O ajuste de **Gradle JDK** na IDE é um detalhe local do ambiente e pode não entrar no versionamento, dependendo do `.gitignore`.

Se a IDE voltar a mostrar erro de incompatibilidade entre Gradle e Java, ajuste em:

- **Settings > Build, Execution, Deployment > Build Tools > Gradle > Gradle JDK**

Valor recomendado neste projeto:

- `JBR 17`

## Próximo passo prático

A próxima branch cumulativa de desenvolvimento será:

- `aula/06-navigation-component`

Ela deve nascer da base técnica estável atual e substituir a navegação manual entre `Activity` por navegação estruturada com o **Navigation Component**.

## Fluxo recomendado a cada aula

1. partir da branch anterior já estável;
2. criar a branch da nova aula;
3. implementar somente o escopo da aula;
4. validar build e funcionamento;
5. fazer merge da branch da aula;
6. usar o resultado como base da próxima aula.

## Comandos úteis

Criar a próxima branch a partir da base atual:

```zsh
git switch chore/gradle-jdk-compativel
git switch -c aula/06-navigation-component
```

Validar build:

```zsh
./gradlew assembleDebug
```

## Situação atual observada

O projeto já possui uma base visual e funcional inicial com:

- telas em XML;
- lista com `RecyclerView`;
- cadastro de tarefa em memória;
- tela de detalhes;
- splash screen;
- componentes visuais do Material.

Ainda vamos alinhar a implementação com a trilha das aulas, deixando cada etapa fechada e cumulativa.


