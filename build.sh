#!/usr/bin/env bash

# 1. Atualizar os repositórios de pacotes do sistema
apt-get update

# 2. Instalar dependências do sistema necessárias
apt-get install -y python3-distutils zlib1g-dev build-essential gfortran libatlas-base-dev

# 3. Atualizar o pip para garantir que estamos usando a versão mais recente
pip install --upgrade pip
