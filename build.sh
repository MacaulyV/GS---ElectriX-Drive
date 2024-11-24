#!/usr/bin/env bash

# Atualizar os repositórios de pacotes do sistema
apt-get update

# Instalar as dependências essenciais para o Python e compilação
apt-get install -y python3-distutils zlib1g-dev build-essential

# Atualizar o pip para garantir que estamos usando a versão mais recente
pip install --upgrade pip
