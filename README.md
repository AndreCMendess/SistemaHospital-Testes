# Sistema Hospitalar - Cadastro e Visualização de Pacientes

## Descrição
Este projeto faz parte de um sistema hospitalar com foco na funcionalidade de cadastro e visualização de pacientes. Ele foi desenvolvido como uma atividade para corrigir bugs propositalmente inseridos no código, criar casos de teste, aplicar validações, e garantir o correto funcionamento do sistema.

## Funcionalidades

### Tela de Cadastro de Pacientes
- **Cadastro de Pacientes**: Permite o cadastro de novos pacientes no sistema com as seguintes informações obrigatórias:
  - Nome completo
  - CPF
  - Data de nascimento
  - Endereço
  - Telefone
  - Associação com um convênio

- **Validação dos Campos**: O sistema valida se os campos obrigatórios foram preenchidos corretamente e impede o cadastro se houver erros.

- **Associação de Convênio**: Permite associar um convênio já cadastrado no sistema ao paciente.

### Tela de Visualização de Pacientes
- **Visualização Automática**: Ao abrir a tela, os dados de todos os pacientes cadastrados são exibidos automaticamente.

- **Filtragem de Pacientes**: O sistema permite filtrar a tabela de pacientes por:
  - ID
  - CPF
  - Nome do paciente

- **Limpeza de Filtro**: Após aplicar um filtro, há uma opção para limpar e exibir novamente todos os pacientes cadastrados.

## Requisitos e Regras de Negócio

### Cadastro de Pacientes
- **Nome**: Campo obrigatório, com no máximo 55 caracteres.
- **CPF**: Campo obrigatório, contendo 11 caracteres e único no sistema.
- **Data de Nascimento**: Deve ser uma data válida (DD/MM/AAAA).
- **Endereço**: Campo obrigatório, com no máximo 200 caracteres.
- **Telefone**: Campo obrigatório, com no máximo 15 caracteres no formato (xx)xxxx-xxxx.
- **E-mail**: Campo opcional, mas deve ser válido se preenchido.
- **Convênio**: O paciente deve estar associado a um convênio já cadastrado no sistema.

### Visualização de Pacientes
- **Preenchimento Automático**: A tabela é preenchida automaticamente ao abrir a janela.
- **Filtragem**: Permite filtrar por ID (apenas numérico), CPF (apenas numérico), e nome (alfabético).
- **Limpeza de Filtro**: Remove o filtro aplicado e exibe todos os pacientes cadastrados.
