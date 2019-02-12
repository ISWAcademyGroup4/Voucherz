CREATE TABLE [IsActive]
(
	Id INT PRIMARY KEY IDENTITY(1,1) NOT NULL,
	Active BIT DEFAULT(1) NOT NULL,
	InActive BIT DEFAULT(0) NOT NULL
)

Go

INSERT INTO IsActive(Active,InActive) VALUES(1,0)

GO

ALTER PROCEDURE [dbo].[uspIsActive]
	-- Add the parameters for the stored procedure here
	@active NVARCHAR(5),
	@email Nvarchar(50)
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;
    -- Insert statements for procedure here
	IF @active = 'true'
	UPDATE Users
	SET Active = ~Active WHERE Email = @email
	ELSE
	IF @active = 'false'
	UPDATE Users
	SET Active = ~Active WHERE Email = @email
END