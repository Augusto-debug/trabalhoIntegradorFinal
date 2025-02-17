function validarSenha(senha) {
    const regexSenha = /^(?=.*[A-Za-z])(?=.*\d).{6,}$/;
    return regexSenha.test(senha);
}

document.getElementById('formCadastro').addEventListener('submit', function(event) {
    const usuario = document.getElementById('usuario').value.trim();
    const senha = document.getElementById('senha').value;
    const confirmaSenha = document.getElementById('txtCheckPwd').value;
    const mensagemErro = document.getElementById('mensagemErro');

    mensagemErro.style.display = 'none';
    mensagemErro.textContent = '';

    if (usuario === '' || senha === '' || confirmaSenha === '') {
        mensagemErro.textContent = 'Todos os campos são obrigatórios.';
        mensagemErro.style.display = 'block';
        event.preventDefault();
        return;
    }

    if (!validarSenha(senha)) {
        mensagemErro.textContent = 'A senha deve ter pelo menos 6 caracteres, incluindo pelo menos uma letra e um número.';
        mensagemErro.style.display = 'block';
        event.preventDefault();
        return;
    }

    if (senha !== confirmaSenha) {
        mensagemErro.textContent = 'As senhas não coincidem.';
        mensagemErro.style.display = 'block';
        event.preventDefault();
    }
});
