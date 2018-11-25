describe("Test tests", () => {
    it("Visits site", () => {
        cy.visit("routinestarter.herokuapp.com");
        cy.contains("Try it").click();

        cy.contains("SIIN2 PEAKS");
    });
});