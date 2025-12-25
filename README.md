# Gestão de Usuário com Firebase Auth

Um aplicativo Android moderno para gerenciamento de usuários utilizando **Firebase Authentication** e **Firestore**.

O projeto serve como base para apps que precisam de autenticação, cadastro, atualização de perfil, upload de foto e exclusão de conta.

## Tecnologias Utilizadas

- **Linguagem**: 100% Kotlin
- **UI**: Jetpack Compose
- **Arquitetura**: Clean Architecture (camadas: presentation, domain, data)
- **Injeção de Dependência**: Hilt (Dagger)
- **Gerenciamento de Estado**: StateFlow + ViewModel
- **Navegação**: Jetpack Navigation Compose
- **Banco de Dados / Autenticação**: Firebase Auth + Firestore
- **Coroutines** para operações assíncronas

## Funcionalidades Atuais

- Cadastro de usuário (email/senha)
- Login
- Recuperação de dados do usuário autenticado
- Atualização de foto de perfil (upload para Firebase Storage)
- Deleção permanente da conta (com reautenticação)
- Tratamento de estados (Loading, Success, Error) por ação
- Navegação segura com limpeza de back stack (ex: logout/deleção volta para Login como raiz)

## Estrutura do Projeto

```
app/
├── data/               # Repositórios e implementação de fontes de dados (Firebase)
├── domain/             # Modelos, UseCases e interfaces de repositório
├── presentation/       # Telas (Compose), ViewModels e estados de UI
│   ├── common/         # UiState genérico
│   ├── configuracoes/  # Tela de configurações e ViewModel
│   └── ...             # Outras telas
├── di/                 # Módulos Hilt
```

## Como Rodar o Projeto

1. Clone o repositório:
   ```bash
   git clone https://github.com/valdomiro22/gestao_usuario_firebase.git
   ```

2. Abra o projeto no **Android Studio** (versão Hedgehog ou superior recomendada).

3. Adicione o arquivo `google-services.json` na pasta `app/`:
   - Crie um projeto no [Firebase Console](https://console.firebase.google.com/)
   - Ative **Authentication** (Email/Password)
   - Ative **Firestore** e **Storage** (se for usar upload de foto)
   - Baixe o `google-services.json` e coloque na raiz do módulo `app`

4. Sincronize o Gradle e rode o app em um emulador ou dispositivo físico.

## Próximos Passos (em desenvolvimento)

- Tela Home após login
- Recuperação de senha
- Edição de nome e outros dados do perfil
- Validações mais robustas
- Testes unitários e de UI
- Suporte a login com Google
- Pull-to-refresh e tratamento offline

## Contribuição

Sinta-se à vontade para abrir issues ou pull requests.  
O projeto está em evolução constante e serve como base de estudo/aprendizado de Jetpack Compose + Clean Architecture.

## Licença

MIT License – use, modifique e distribua livremente.

---

**Autor**: Valdomiro Santos  
**GitHub**: [valdomiro22](https://github.com/valdomiro22)

Qualquer dúvida, abra uma issue que eu ajudo! 🚀
