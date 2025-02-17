
function validarSenha(senha) {
    const regexSenha = /^(?=.*[A-Za-z])(?=.*\d).{6,}$/;
    return regexSenha.test(senha);
}

document.getElementById('formLogin').addEventListener('submit', function(event) {
    const senha = document.getElementById('senha').value;
    const usuario = document.getElementById('usuario').value;
    if (usuario === '' || senha === '') {
        alert('Usuário e senha são obrigatórios.');
        event.preventDefault();
    }
    if (!validarSenha(senha)) {
        alert('A senha deve ter pelo menos 6 caracteres, incluindo pelo menos uma letra e um número.');
        event.preventDefault();
    }
});