$ErrorActionPreference = 'Stop'
$buildDir = Join-Path $PSScriptRoot 'build'
New-Item -ItemType Directory -Force -Path $buildDir | Out-Null
$fontes = @(Get-ChildItem (Join-Path $PSScriptRoot 'src') -Recurse -Filter '*.java' | ForEach-Object FullName)
& javac --release 21 -encoding UTF-8 -d $buildDir @fontes
if ($LASTEXITCODE -ne 0) { throw 'Falha de compilacao' }
& java -cp $buildDir br.com.bytebank.Testes
if ($LASTEXITCODE -ne 0) { throw 'Testes falharam' }
